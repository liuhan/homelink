package com.smart.homelink.netty.server;

import com.alibaba.fastjson.JSON;
import com.smart.homelink.dto.CloudCabinetVo;
import com.smart.homelink.netty.NettyChannelMap;
import com.smart.homelink.netty.NettyUtil;
import com.smart.homelink.netty.model.NettyMessage;
import com.smart.homelink.netty.util.NettyIMMessageType;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Netty server handler
 * Created by think on 2018/3/28.
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    Logger logger = LoggerFactory.getLogger(NettyServerHandler.class);

    private String serverIP ;//服务器I
    private int serverPort;// 服务器端口号
    private int serverHttpPort;//服务端http端口
    private String uuid;
    public NettyServerHandler(String serverIP , int serverPort , int serverHttpPort , String uuid){
        this.serverIP = serverIP;
        this.serverPort = serverPort;
        this.serverHttpPort = serverHttpPort;
        this.uuid = uuid;
    }


    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
       //
    }

    /**
     * 连接
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception { // (5)
        Channel channel = ctx.channel();
        logger.info("connect start ------------》{}", channel.toString());
        //第一次连接发送心跳检测
        NettyMessage nettyMessage = new NettyMessage();
        nettyMessage.setCode(NettyIMMessageType.CLOUD_ABINET_SERVER_ONLINE);
        ctx.channel().writeAndFlush(nettyMessage);
    }
    /**
     * 读取数据        //回写响应消息
     * @param ctx
     * @param msg
     * @throws UnsupportedEncodingException
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Channel channel = ctx.channel();

        if(msg instanceof NettyMessage){
            NettyMessage nettyMessage = (NettyMessage)msg;
            if(!NettyUtil.validateSign(nettyMessage)){
                logger.error("签名错误 code:{},terminalNo:{}，data:{}" ,nettyMessage.getCode(), nettyMessage.getTerminalNo(), nettyMessage.getData() );
                return;
            }
            Map paramMap = (Map) nettyMessage.getData();
            NettyMessage sendNettyMessage = new NettyMessage();
            switch (nettyMessage.getCode())
            {
                case NettyIMMessageType.CLOUD_ABINET_CLIENT_REGISTE:  //注册
                    logger.info("------>注册开始");
                    CloudCabinetVo vo = new CloudCabinetVo();
                    vo.setTerminalNo(nettyMessage.getTerminalNo());
                    vo.setIpAddress(channel.remoteAddress().toString());
                    vo.setMacAddress(paramMap.get("macAddress").toString());
                    vo.setCpuSn(paramMap.get("cpuSn").toString());
                    vo.setApkVersion(paramMap.get("apkVersion").toString());
                    if (NettyChannelMap.addChannel(nettyMessage.getTerminalNo() , ctx ,vo, this.serverIP , this.serverPort ,this.serverHttpPort,this.uuid)){
                        sendNettyMessage.setCode(NettyIMMessageType.CLOUD_ABINET_SERVER_REGISTE_SUCCESS);
                        ctx.channel().writeAndFlush(sendNettyMessage);
                    }else{
                        sendNettyMessage.setCode(NettyIMMessageType.CLOUD_ABINET_SERVER_REGISTE_FAIL);
                        ctx.channel().writeAndFlush(sendNettyMessage);
                    }
                    break;
                case NettyIMMessageType.SYS_APPLICATION_CLIENT_REGISTER: //应用系统注册,暂被废弃
                    logger.info("应用系统注册，设备编号:{}" , nettyMessage.getTerminalNo());
                    Map map = new HashMap();
                    map.put("serverIp" , this.serverIP );
                    sendNettyMessage.setData(map);
                    sendNettyMessage.setCode(NettyIMMessageType.SYS_APPLICATION_SERVER_RETURN_REGISTER);
                    ctx.channel().writeAndFlush(sendNettyMessage);
                    break;
                case NettyIMMessageType.CLOUD_ABINET_CLIENT_OPEN_DOOR_SUCCESS:
                    logger.error("开门成功，设备编号:{}" , nettyMessage.getTerminalNo());
                    NettyUtil.sendOpenCloseDoor(nettyMessage , ctx);
                    break;
                case NettyIMMessageType.CLOUD_ABINET_CLIENT_USER_OPEN_DOOR:
                    logger.error("用户开门，设备编号:{}" , nettyMessage.getTerminalNo());
                    NettyUtil.sendOpenCloseDoor(nettyMessage , ctx);
                    break;
                case NettyIMMessageType.CLOUD_ABINET_CLIENT_RELEASE_TERMINAL:
                    logger.error("释放设备资源，设备编号:{}" , nettyMessage.getTerminalNo());
                    NettyUtil.sendOpenCloseDoor(nettyMessage , ctx);
                    break;
                case NettyIMMessageType.CLOUD_ABINET_CLIENT_WEIGHT_EXCEPTION:
                    logger.error("计重模块异常，设备编号:{}" , nettyMessage.getTerminalNo());
                    NettyUtil.sendExceptionMQ(nettyMessage , ctx);
                    break;
                case NettyIMMessageType.CLOUD_ABINET_CLIENT_ADVERT_PLAY:
//                    logger.error("广告播放记录，设备编号:{}" , nettyMessage.getTerminalNo());
//                    NettyUtil.sendProgramPlayMQ(nettyMessage , ctx);
                    break;
                case NettyIMMessageType.SYS_CLOUD_ABINET_CLIENT_GOODS_DETAIL:
                    logger.error("云柜端发送商品明细:{}" , JSON.toJSONString(nettyMessage.getExtData()));
                    //NettyUtil.sendUser4WebsocketGoodsDetail(nettyMessage , ctx);//云柜端发送商品明细
                    break;
                case NettyIMMessageType.CONSOLE_APPLICATION_CLOUD_ABINET_CLIENT_ON_OFF_DOOR_WEIGHT_VOLUME:
                    logger.error("云柜客户端发送柜子开关门状态 0-关门 1-开门 + 重量 + 音量:{}" , JSON.toJSONString(nettyMessage.getExtData()));
                    NettyUtil.sendConsoleUser4WebsocketTerminalInfo(nettyMessage , ctx);//云柜端发送商品明细
                    break;
                case NettyIMMessageType.CONSOLE_APPLICATION_CLOUD_ABINET_CLIENT_SEND_NO_PAY_ORDER:
                    logger.error("云柜客户端发送未处理的订单，包括订单信息:{}" , JSON.toJSONString(nettyMessage.getExtData()));
                    NettyUtil.sendConsoleUser4WebsocketTerminalInfo(nettyMessage , ctx);//云柜端发送商品明细
                    break;
                case NettyIMMessageType.CONSOLE_APPLICATION_CLOUD_ABINET_CLIENT_GET_GOODS_TABLE_TYPE:
                    logger.error("云柜客户端发送柜子商品表类型（层数）" , JSON.toJSONString(nettyMessage.getExtData()));
                    NettyUtil.sendConsoleUser4WebsocketTerminalInfo(nettyMessage , ctx);//云柜端发送商品明细
                    break;
                case NettyIMMessageType.SYS_APPLICATION_CLIENT_OPEN_DOOR:
                    ChannelHandlerContext channel1 = NettyChannelMap.CLOUD_ABINET_CHANNEL.get(nettyMessage.getTerminalNo());
                    if(channel1 != null){
                        sendNettyMessage.setTerminalNo(nettyMessage.getTerminalNo());
                        sendNettyMessage.setCode(NettyIMMessageType.CLOUD_ABINET_SERVER_OPEN_DOOR);
                        channel1.writeAndFlush(sendNettyMessage);
                        logger.error("发送开/关门成功，设备编号:{}" , nettyMessage.getTerminalNo());

                    }else{
                        logger.error("发送开/关门失败，设备编号:{}" , nettyMessage.getTerminalNo());
                    }

                    break;
                default:
                    logger.error("IM 无效的类型 ,code:{}",nettyMessage.getCode());
                    break;
            }
        }else{
            logger.error("服务端读取未知数据-->{}" , msg);
        }
    }

    /**
     * 异常处理或者掉线
     * @param ctx
     * @param cause
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error("云柜connect exception-------->{} message --> " , ctx.channel().toString() ,cause.getMessage());
        NettyChannelMap.removeChannel(ctx ,this.serverIP ,this.uuid);
        cause.printStackTrace();
        ctx.close();
    }

  /*
    掉线
   */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        logger.error("云柜connect timeout------->{}" , ctx.channel().toString());
        NettyChannelMap.removeChannel(ctx ,this.serverIP ,this.uuid);
        super.channelInactive(ctx);
    }
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }
}
