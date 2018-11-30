package com.kelent.netty.server;

import com.alibaba.fastjson.JSON;
import com.kelent.constant.SysConst;
import com.kelent.dto.console.AdminUserIdentity;
import com.kelent.dto.user.AppUserIdentity;
import com.kelent.netty.NettyChannelMap;
import com.kelent.netty.NettyUtil;
import com.kelent.secucity.SignUtil;
import com.kelent.type.CommonReturnMessageType;
import com.kelent.util.DateUtil;
import com.kelent.util.ObjectUtil;
import com.kelent.util.SystemTokenUtil;
import com.kelent.util.Utility;
import com.kelent.util.netty.NettyIMMessageType;
import com.kelent.util.netty.model.NettyMessage;
import com.qiniu.util.StringUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.multipart.Attribute;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Netty server handler
 * Created by think on 2018/3/28.
 */
public class NettyHttpServerHandler extends ChannelInboundHandlerAdapter {
    Logger logger = LoggerFactory.getLogger(NettyHttpServerHandler.class);

    private WebSocketServerHandshaker handshaker;
    /**
     * 读取数据        //回写响应消息
     * @param ctx
     * @param msg
     * @throws UnsupportedEncodingException
     */

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Channel channel = ctx.channel();
        String returnCode = CommonReturnMessageType.SUCCESS.getCode();
        String returData = "";
        if(msg instanceof FullHttpRequest) {
            FullHttpRequest request = (FullHttpRequest) msg;
            String url = request.uri();
            Map<String, String> paramMap = new HashMap<String, String>();
            if (request.method().name().equals(HttpMethod.GET.name())) {  //获取get的参数
                QueryStringDecoder parmDecoder = new QueryStringDecoder(url);
                Map<String, List<String>> params = parmDecoder.parameters();
                if (!params.isEmpty()) {
                    for(String key : params.keySet()){
                        paramMap.put(key, params.get(key).get(0));
                    }
                }
            } else if (request.method().name().equals(HttpMethod.POST.name())) {     //post 请求
                HttpPostRequestDecoder decoder = new HttpPostRequestDecoder(request);
                decoder.offer((HttpContent) request);
                List<InterfaceHttpData> parmList = decoder.getBodyHttpDatas();
                for (InterfaceHttpData parm : parmList) {
                    Attribute data = (Attribute) parm;
                    paramMap.put(data.getName(), data.getValue());
                }
            }
            //必须是websocket
            if (paramMap.containsKey("token") && paramMap.containsKey("websocket")) {
                handleHttpRequest( ctx, request , paramMap );
            }else{
                returnCode =  sendData( paramMap);
            }
        } else if (msg instanceof WebSocketFrame) {//websocket
            handlerWebSocketFrame(ctx, (WebSocketFrame) msg);
            return;
        }else{
            //进行返回404
            returnCode = CommonReturnMessageType.IM_HTTP_REQUEST_ERROR.getCode();
        }
        String messge = "{code:\"" + returnCode + "\",data:\"" + returData + "\"}";
        write(ctx, messge, null);
    }

    /**
     * 处理websocket
     * @param ctx
     * @param frame
     */
    private void handlerWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {
        // 判断是否关闭链路的指令
        if (frame instanceof CloseWebSocketFrame) {
            handshaker.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());
            return;
        }
        // 判断是否ping消息
        if (frame instanceof PingWebSocketFrame) {
            ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
            return;
        }
        // 本例程仅支持文本消息，不支持二进制消息
        if (!(frame instanceof TextWebSocketFrame)) {
            throw new UnsupportedOperationException(String.format("%s frame types not supported", frame.getClass().getName()));
        }
        // 返回应答消息
        String requestText = ((TextWebSocketFrame) frame).text();
        logger.info("服务端收到websocket消息：" + requestText);
        ctx.channel().writeAndFlush(
                new TextWebSocketFrame("{\"data\":\"发送数据：" + requestText + "," + DateUtil.getCurDateTimeStr()+ "\"}")
        );
        String  userId_terminalNo = NettyChannelMap.getConsoleUserIdByChannel(ctx);
        if(userId_terminalNo != null && !StringUtils.isNullOrEmpty(requestText)){
            String[] userId_terminalNo_array = userId_terminalNo.split("_");
            if(userId_terminalNo_array.length == 2){
                try {
                    if(requestText.equals(NettyIMMessageType.CONSOLE_APPLICATION_SERVER_ON_OFF_DOOR_WEIGHT_VOLUME + "") ||
                            requestText.equals(NettyIMMessageType.CONSOLE_APPLICATION_SERVER_GET_NO_PAY_ORDER + "" )||
                            requestText.equals(NettyIMMessageType.CONSOLE_APPLICATION_SERVER_GET_GOODS_TABLE_TYPE + "")  ){
                        String terminalNo = userId_terminalNo_array[1];
                        String userId = userId_terminalNo_array[0];
                        Map paramMap = new HashMap();
                        Map map = SignUtil.getSignMap();
                        map.put("terminalNo", terminalNo);
                        SignUtil.getSign(map);
                        paramMap.put("code", requestText);
                        paramMap.put("terminalNo", terminalNo);
                        paramMap.put("userId", userId);
                        paramMap.put("data", JSON.toJSONString(map));
                        sendData( paramMap);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }
    }

    /**
     * websocket  删除
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        logger.info("websocket---掉线" + ctx.channel());
        NettyChannelMap.removeUserWebsocketChannel(ctx);
    }

    /**
     * 处理websocket 登录请求
     * @param ctx
     * @param req
     */
    private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest req ,   Map<String, String> paramMap ) {
        if (!req.decoderResult().isSuccess() || (!"websocket".equals(req.headers().get("Upgrade")))) {
            sendHttpResponse(ctx, req, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
            return;
        }
        //登录
        AppUserIdentity appUserIdentity = null;
        AdminUserIdentity adminUserIdentity = null;
        String token = paramMap.get("token");
        if(!Utility.isNullorEmpty(token)){
            try {
                if(token.startsWith(SysConst.CONSOLE_TOKEN_PREFIX)){//web后台
                    adminUserIdentity = SystemTokenUtil.getConosleUserTokenModelByToken(token);
                }else if (token.startsWith(SysConst.WECHAT_TOKEN_PREFIX)){  //微信端
                    appUserIdentity = SystemTokenUtil.getWechatUserTokenModelByToken(paramMap.get("token").toString());
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }
        logger.info("appUserIdentity----------->" + appUserIdentity);
        if (appUserIdentity == null && adminUserIdentity== null) {
            sendHttpResponse(ctx, req, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
            return;
        }
        WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(getWebSocketLocation(req) , null, false);
        handshaker = wsFactory.newHandshaker(req);
        if (handshaker == null) {
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
        } else {
            handshaker.handshake(ctx.channel(), req);
        }
        if(appUserIdentity != null){   //添加微信用户
            NettyChannelMap.addUserWebsocketChannel(appUserIdentity.getId() , ctx);
        }
        if(adminUserIdentity != null){ //添加后台
            NettyChannelMap.addConsoleUserWebsocketChannel(adminUserIdentity.getId() ,  paramMap.get("terminalNo"), ctx);
            TextWebSocketFrame text = new TextWebSocketFrame("{\"data\":\"Netty webSocket服务连接成功" + DateUtil.getCurDateTimeStr()+ "\"}");
            ctx.channel().writeAndFlush(text);
        }
    }
    private static void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req, DefaultFullHttpResponse res) {
        // 返回应答给客户端
        if (res.status().code() != 200) {
            ByteBuf buf = Unpooled.copiedBuffer(res.status().toString(), CharsetUtil.UTF_8);
            res.content().writeBytes(buf);
            buf.release();
        }
        // 如果是非Keep-Alive，关闭连接
        ChannelFuture f = ctx.channel().writeAndFlush(res);
        if (!isKeepAlive(req) || res.status().code() != 200) {
            f.addListener(ChannelFutureListener.CLOSE);
        }
    }
    private static boolean isKeepAlive(FullHttpRequest req) {
        return false;
    }
    private String getWebSocketLocation(FullHttpRequest req) {
        return "ws://" +  req.headers().get(HttpHeaderNames.HOST) + "/websocket";
    }

    /**
     * 发送数据
     * @param paramMap
     * @return
     * @throws Exception
     */
    public  String   sendData(Map<String, String> paramMap) throws Exception{
        NettyMessage nettyMessage = (NettyMessage) ObjectUtil.mapToObject(paramMap, NettyMessage.class);
        if(Utility.isNullorEmpty(nettyMessage.getData())) return  CommonReturnMessageType.SIGN_ERROR.getCode();
        Map map = (Map) JSON.parse(nettyMessage.getData().toString());
        nettyMessage.setData(map);
        if(!NettyUtil.validateSign(nettyMessage)){
            logger.error("签名错误 terminalNo:{}，data:{}" , nettyMessage.getCode(), nettyMessage.getData() );
             return   CommonReturnMessageType.SIGN_ERROR.getCode();
        }
        ChannelHandlerContext channel  = NettyChannelMap.CLOUD_ABINET_CHANNEL.get(nettyMessage.getTerminalNo());
        if (channel == null) {
            logger.error("开门失败,找不到柜子，设备编号----->" + nettyMessage.getTerminalNo());
            return  CommonReturnMessageType.IM_HTTP_REQUEST_OFF_LINE.getCode();
        }
        switch (nettyMessage.getCode()) {
            case NettyIMMessageType.SYS_APPLICATION_CLIENT_OPEN_DOOR:
                nettyMessage.setCode(NettyIMMessageType.CLOUD_ABINET_SERVER_OPEN_DOOR);
                channel.writeAndFlush(nettyMessage);
                logger.info("微信发送开门---->" + channel.toString());
                NettyUtil.sendOpenCloseDoor(nettyMessage , channel);
                break;
            case NettyIMMessageType.CLOUD_ABINET_SERVER_APP_OPEN_DOOR:
                channel.writeAndFlush(nettyMessage);
                logger.info("APP发送开门---->" + channel.toString());
                NettyUtil.sendOpenCloseDoor(nettyMessage , channel);
                break;
            case NettyIMMessageType.CLOUD_ABINET_SERVER_UPDATE_GOODS_TABLE:
                channel.writeAndFlush(nettyMessage);
                logger.error("发送更新商品表");
                break;
            case NettyIMMessageType.CLOUD_ABINET_SERVER_ADVERT:
                channel.writeAndFlush(nettyMessage);
                logger.error("广告更新");
                break;
            case NettyIMMessageType.CLOUD_ABINET_SERVER_ALARMANDADVERTAND_VOLUME_SCREEN_BRIGHTNESS:
                    channel.writeAndFlush(nettyMessage);
                    logger.error("更新音量、亮度");
                break;
            case NettyIMMessageType.CLOUD_ABINET_SERVER_REBOT:
                    channel.writeAndFlush(nettyMessage);
                    logger.error("重启");
                break;
            case NettyIMMessageType.CLOUD_ABINET_SERVER_AKP_UPDATE:
                    channel.writeAndFlush(nettyMessage);
                    logger.info("APK升级");
                break;
            case NettyIMMessageType.CLOUD_ABINET_SERVER_ON_OFF:
                    channel.writeAndFlush(nettyMessage);
                    logger.info("APK更新开关机时间");
                break;
            case NettyIMMessageType.CLOUD_ABINET_SERVER_UPDATE_PASSWORD:
                    channel.writeAndFlush(nettyMessage);
                    logger.info("APK更新密码");
                break;
            case NettyIMMessageType.CLOUD_ABINET_SERVER_UPDATE_QRCODE:
                channel.writeAndFlush(nettyMessage);
                logger.info("APK更新二维码");
                break;
            case NettyIMMessageType.CLOUD_ABINET_SERVER_UPDATE_WEIGHT_WARNING_ERROR_RANGE:
                channel.writeAndFlush(nettyMessage);
                logger.info("UPDATE_WEIGHT_WARNING_ERROR_RANGE:云柜设备重量警告容错范围g");
                break;
            case NettyIMMessageType.CLOUD_ABINET_SERVER_UPDATE_WEIGHT_LAYER_ERROR_RANGE:
                channel.writeAndFlush(nettyMessage);
                logger.info("UPDATE_WEIGHT_LAYER_ERROR_RANGE:云柜设备重量每层容错范围g");
                break;
            case NettyIMMessageType.CONSOLE_APPLICATION_SERVER_ON_OFF_DOOR_WEIGHT_VOLUME:
                channel.writeAndFlush(nettyMessage);
                logger.info("CONSOLE_APPLICATION_SERVER_ON_OFF_DOOR_WEIGHT_VOLUME:console 后台发送获取开关门状态 + 重量 + 音量");
                break;
            case NettyIMMessageType.CONSOLE_APPLICATION_SERVER_GET_NO_PAY_ORDER:
                channel.writeAndFlush(nettyMessage);
                logger.info("CONSOLE_APPLICATION_SERVER_GET_NO_PAY_ORDER:console console 后台发送获取是否存在未处理订单");
                break;
            case NettyIMMessageType.CONSOLE_APPLICATION_SERVER_GET_GOODS_TABLE_TYPE:
                channel.writeAndFlush(nettyMessage);
                logger.info("CONSOLE_APPLICATION_SERVER_GET_NO_PAY_ORDER:console console 后台发送获取柜子的商品表类型，5、9、15");
                break;
            case NettyIMMessageType.SYS_APPLICATION_CLIENT_ORDER_PAY_STATUS:
                //给用户客户端发送订单支付消息（websocket）
                NettyUtil.sendUserOrderPayStatus(nettyMessage);
                logger.info("给用户客户端发送订单支付消息");
                break;
        }
        return   CommonReturnMessageType.SUCCESS.getCode();
    }


    //写入，发送回客户端
    private  void write(ChannelHandlerContext ctx,String res,String contentType) {
        byte[] bytes=null;
        try {
            bytes=res.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                HttpResponseStatus.OK, Unpooled.wrappedBuffer(bytes));
        if(contentType!=null){
            response.headers().set(HttpHeaders.Names.CONTENT_TYPE, "text/plain");
        }else{
            response.headers().set(HttpHeaders.Names.CONTENT_TYPE, "text/plain");
        }
        response.headers().set(HttpHeaders.Names.CONTENT_LENGTH,
                response.content().readableBytes());
        ctx.write(response);
        ctx.flush();
    }

    /**
     * 异常处理或者掉线
     * @param ctx
     * @param cause
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error(" http连接关闭" , ctx.channel().toString());
        ctx.close();
    }

}
