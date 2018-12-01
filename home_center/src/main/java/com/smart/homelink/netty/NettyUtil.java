package com.smart.homelink.netty;

import com.alibaba.fastjson.JSON;

import com.smart.homelink.netty.model.NettyMessage;
import com.smart.homelink.timertask.service.NettyServerService;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Map;

/**
 * Created by think on 2018/3/29.
 */
public class NettyUtil {
    private final static Logger logger = LoggerFactory.getLogger(NettyUtil.class);

    /**
     * 发送设备上线、离线
     * @param sendData
     */
    public static  void sendOnOffLineMQ(String sendData){
        //.info(result.toString());
    }

    /**
     * 发送APP用户操作信息
     * @param nettyMessage
     * @param channel
     */
    public static  void sendAppUserOptMQ(NettyMessage nettyMessage , ChannelHandlerContext channel , String remark){
//        TerminalLog terminalLog = new TerminalLog();
//        terminalLog.setTerminalNo(nettyMessage.getTerminalNo());
//        terminalLog.setLevel(TerminalLogLevelType.LEVEL_INFORMATION.getType());
//        terminalLog.setType(TerminalLogType.SYS_USER_OPT.getType());
//        terminalLog.setCreateTime(new Date());
//        terminalLog.setRemark( remark + "：{用户Id：" + nettyMessage.getUserId() + "，操作时间：" +
//                DateUtil.getCurDateTimeStr() +
//                ",重量信息：" + nettyMessage.getExtData() + "}");
//        CloudCabinetVo cloudCabinetVo = NettyChannelMap.CLOUD_ABINET_CHANNEL_TERMINAL_NO.get(channel.channel().id());
//        if(cloudCabinetVo != null){
//            terminalLog.setApkVersion(cloudCabinetVo.getApkVersion());
//        }
//        SendResult result = MQUtil.sendSysMsg( RocketMQTagStatistics.SYSTEM_TERMINAL_APP_USER_OPT.getCode() , terminalLog);
//        logger.info(result.toString());
    }

    /**
     * 发送异常错误信息
     * @param nettyMessage
     * @param channel
     */
    public  static  void sendExceptionMQ(NettyMessage nettyMessage ,ChannelHandlerContext channel ){
//        TerminalLog terminalLog = new TerminalLog();
//        terminalLog.setTerminalNo(nettyMessage.getTerminalNo());
//        terminalLog.setLevel(TerminalLogLevelType.LEVEL_IMPORTANT.getType());
//        terminalLog.setType(TerminalLogType.SYSTEM_EXCEPTION.getType());
//        terminalLog.setRemark("计重模块出现严重问题，"+ nettyMessage.getData() + ";" + channel.channel().remoteAddress().toString());
//        terminalLog.setCreateTime(new Date());
//        CloudCabinetVo cloudCabinetVo = NettyChannelMap.CLOUD_ABINET_CHANNEL_TERMINAL_NO.get(channel.channel().id());
//        if(cloudCabinetVo != null){
//            terminalLog.setApkVersion(cloudCabinetVo.getApkVersion());
//        }
//        SendResult result = MQUtil.sendSysMsg( RocketMQTagStatisticss.SYSTEM_TERMINAL_WEIGHT_EXCEPTION.getCode() , terminalLog);
    }

    /**
     * 广告播放记录
     * @param nettyMessage
     * @param channel
     */
    public  static  void sendProgramPlayMQ(NettyMessage nettyMessage ,ChannelHandlerContext channel ){
//        ProgramPlay program = new ProgramPlay();
//        program.setTerminalNo(nettyMessage.getTerminalNo());
//        program.setCreateTime(new Date());
//        program.setUuid(Utility.getUuid());
//        Map map = (Map) nettyMessage.getData();
//        if( map.get("programId") != null &&  map.get("startTime") != null ){
//            long startTimeL = Utility.parseLong(map.get("startTime").toString());
//            long endTimeL = Utility.parseLong(map.get("endTime").toString());
//            Date startTime = new Date();
//            startTime.setTime(startTimeL);
//            Date endTime = new Date();
//            endTime.setTime(endTimeL);
//            long programId = Utility.parseLong(map.get("programId").toString());
//            program.setStartTime(startTime);
//            program.setEndTime(endTime);
//            program.setProgramId(programId);
//            String programName = map.get("programName") == null ? "" :  map.get("programName").toString();
//            program.setProgramName(programName);
//            Long playTime =  (endTimeL - startTimeL)/1000;
//            program.setPlayingTime(playTime.intValue());
//            SendResult result = MQUtil.sendSysMsg( RocketMQTagStatistics.SYSTEM_TERMINAL_CLOUD_ADVERT_PLAY.getCode() , program);
//        }
    }
    /**
     * 云柜端发送商品明细 给sys系统客户端 --websocket
     * @param nettyMessage
     * @param channel
     */
    public  static  void sendUser4WebsocketGoodsDetail(NettyMessage nettyMessage ,ChannelHandlerContext channel ){
//        logger.info("userid-------------" + nettyMessage.getUserId()  );
//        if(nettyMessage.getUserId() != null && nettyMessage.getExtData() != null){
//            logger.info("USER_WEBSOCKET_CHANNEL-------------" + NettyChannelMap.USER_WEBSOCKET_CHANNEL.size()  );
//            ChannelHandlerContext context = NettyChannelMap.USER_WEBSOCKET_CHANNEL.get(nettyMessage.getUserId());
//            logger.info("ChannelHandlerContext-------------" + context  );
//            if(context != null){
//                NettyWebsocketMessage nettyWebsocketMessage = new NettyWebsocketMessage();
//                nettyWebsocketMessage.setCode(NettyIMWebsocketMessageType.SYS_CLOUD_ABINET_CLIENT_GOODS_DETAIL);
//                nettyWebsocketMessage.setUserId(nettyMessage.getUserId());
//                nettyWebsocketMessage.setTerminalNo(nettyMessage.getTerminalNo());
//                //是全时
//                if ("yum".equals(NettyServerService.getProfile())) {
//                    nettyWebsocketMessage.setData(nettyMessage.getExtData());
//                    //不是全时
//                } else {
//                    nettyWebsocketMessage.setData(OrdersGoodsCalc.getLayerOrderGoods((Map)nettyMessage.getExtData()));
//                }
//
//                String text = JSON.toJSONString(nettyWebsocketMessage);
//                //是全时
//                if ("yum".equals(NettyServerService.getProfile())) {
//                    context.channel().writeAndFlush(new TextWebSocketFrame(text));
//                    //不是全时,暂时去掉
//                } else {
//                   // context.channel().writeAndFlush(new TextWebSocketFrame(text));
//                }
//
//
//            }
//        }
    }

    /**
     * 发送给用户客户端发送订单支付状态
     * @param nettyMessage
     */
    public  static  void sendUserOrderPayStatus(NettyMessage nettyMessage){
//        logger.info("userid-------------" + nettyMessage.getUserId()  );
//        if(nettyMessage.getUserId() != null ){
//            ChannelHandlerContext context = NettyChannelMap.USER_WEBSOCKET_CHANNEL.get(nettyMessage.getUserId());
//            if(context == null){
//                logger.error("websocket-----ChannelHandlerContext-------------null"  );
//                try {
//                    Thread.sleep(2000);
//                }catch (InterruptedException e){}
//                context = NettyChannelMap.USER_WEBSOCKET_CHANNEL.get(nettyMessage.getUserId());
//                logger.info("websocket-----ChannelHandlerContext-------------" +  context  );
//            }
//            if(context != null){
//                NettyWebsocketMessage nettyWebsocketMessage = new NettyWebsocketMessage();
//                nettyWebsocketMessage.setCode(NettyIMWebsocketMessageType.SYS_USER_ORDER_PAY);
//                nettyWebsocketMessage.setUserId(nettyMessage.getUserId());
//                nettyWebsocketMessage.setTerminalNo(nettyMessage.getTerminalNo());
//                nettyWebsocketMessage.setData(nettyMessage.getData());
//                String text = JSON.toJSONString(nettyWebsocketMessage);
//                context.channel().writeAndFlush(new TextWebSocketFrame(text));
//            }
//        }
    }

    /**
     * 云柜端发送设备信息给console端
     * @param nettyMessage
     * @param channel
     */
    public  static  void sendConsoleUser4WebsocketTerminalInfo(NettyMessage nettyMessage ,ChannelHandlerContext channel ){
//        logger.info("userid-------------" + nettyMessage.getUserId()  + "---extData-->" + nettyMessage.getExtData()  );
//        if(nettyMessage.getUserId() != null ){
//            logger.info("USER_WEBSOCKET_CHANNEL-------------" + NettyChannelMap.CONSOLE_USER_WEBSOCKET_CHANNEL.size()  );
//            ChannelHandlerContext context = NettyChannelMap.CONSOLE_USER_WEBSOCKET_CHANNEL.get(nettyMessage.getUserId() + "_" + nettyMessage.getTerminalNo() );
//            logger.info("ChannelHandlerContext-------------" + context  );
//            if(context != null){
//                NettyWebsocketMessage nettyWebsocketMessage = new NettyWebsocketMessage();
//                nettyWebsocketMessage.setCode(nettyMessage.getCode());
//                nettyWebsocketMessage.setUserId(nettyMessage.getUserId());
//                nettyWebsocketMessage.setTerminalNo(nettyMessage.getTerminalNo());
//                nettyWebsocketMessage.setData(nettyMessage.getExtData());
//                String text = JSON.toJSONString(nettyWebsocketMessage);
//                context.channel().writeAndFlush(new TextWebSocketFrame(text));
//            }
//        }
    }

    /**
     * 记录开关门
     * @param nettyMessage
     */
    public static void sendOpenCloseDoor(NettyMessage nettyMessage  ,  ChannelHandlerContext channel){
//        String info = "{termialNo:\"" + nettyMessage.getTerminalNo() + "\",userId:" +  nettyMessage.getUserId()
//                + ",createTime:\"" + DateUtil.getCurDateTimeStr() + "\"";
//        Map paramMap = (Map) nettyMessage.getData();
//        switch (nettyMessage.getCode()){
//            case NettyIMMessageType.CLOUD_ABINET_SERVER_OPEN_DOOR:  //微信开门
//                sendAppUserOptMQ(nettyMessage , channel , "appuser用户发送开门");//记录开门日志
//                break;
//            case NettyIMMessageType.CLOUD_ABINET_SERVER_APP_OPEN_DOOR:  //APP开门
//                sendAppUserOptMQ(nettyMessage , channel , "console用户发送开门");//记录开门日志
//                break;
//            case NettyIMMessageType.CLOUD_ABINET_CLIENT_OPEN_DOOR_SUCCESS:  //开门成功
//                String userType = "app_user";
//                if(paramMap.get("userType") != null){
//                    userType = paramMap.get("userType").toString();
//                }
//                info = info + ",userType:\"" + userType + "\"}";
//                RedisStatic.getRedisUtil().hset(RedisKey.HASH_TERMINAL_OPENDOOR_RECORD,nettyMessage.getTerminalNo(),info );
//                if("app_user".equals(userType)){//如果是appUser用户开门记录
//                    RedisStatic.getRedisUtil().setex(RedisKey.STRING_CLOUD_CABINET_APPUSER_USE_STATUS + nettyMessage.getUserId(),
//                            RedisKey.CLOUD_CABINET_TERMINAL_USE_STATUS_VALID_TIME , nettyMessage.getTerminalNo());
//
//                }
//                sendAppUserOptMQ(nettyMessage , channel , "云柜开门成功");//记录开门日志
//                break;
//            case NettyIMMessageType.CLOUD_ABINET_CLIENT_USER_OPEN_DOOR:  //用户开门
//                sendAppUserOptMQ(nettyMessage , channel , "用户开门");//记录开门日志
//                break;
//            case NettyIMMessageType.CLOUD_ABINET_CLIENT_RELEASE_TERMINAL:
//               //关门删除redis记录的开门记录
//                RedisStatic.getRedisUtil().hdel(RedisKey.HASH_TERMINAL_OPENDOOR_RECORD,nettyMessage.getTerminalNo());
//                //发送存储用户操作记录
//                sendAppUserOptMQ( nettyMessage , channel , "用户关门");
//                //释放资源
//                try {
//                    Thread.sleep(3000);
//                    RedisStatic.getRedisUtil().del(RedisKey.STRING_CLOUD_CABINET_TERMINAL_USE_STATUS + nettyMessage.getTerminalNo() );//释放设备
//                    RedisStatic.getRedisUtil().del(RedisKey.STRING_CLOUD_CABINET_APPUSER_USE_STATUS + nettyMessage.getUserId());//释放用户
//                }catch (InterruptedException e){}
//
//                break;
//        }
    }

    /**
     * 广播发送  暂不做 不安全
     * @param msg
     * @return
     */
    public static boolean sendAllMessage(String msg){
        NettyChannelMap.NETTY_CHANNEL_GROUP.writeAndFlush(msg);
        return true;
    }

    /**
     * 检查签名是否有效
     * @param nettyMessage
     * @return
     */
    public static boolean validateSign(NettyMessage nettyMessage){
//        if(!Utility.isNullorEmpty(nettyMessage.getTerminalNo())){
//            Map map = (Map) nettyMessage.getData();
//            return  SignUtil.validateSign(map);
//        }
        return false;
    }
}
