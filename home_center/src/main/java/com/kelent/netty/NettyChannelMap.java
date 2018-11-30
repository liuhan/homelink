package com.kelent.netty;

import com.smart.util.IPUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by think on 2018/3/29.
 */
public class NettyChannelMap {

    public final static Logger logger = LoggerFactory.getLogger(NettyChannelMap.class);

    //云柜通道 key-->云柜TerminalNo value-->通道
    public static Map<String , ChannelHandlerContext> CLOUD_ABINET_CHANNEL = new HashMap<String , ChannelHandlerContext>();

    //云柜通道 key--> 通道Id value-->云柜Terminal信息
    public static Map<ChannelId, CloudCabinetVo> CLOUD_ABINET_CHANNEL_TERMINAL_NO = new HashMap<ChannelId , CloudCabinetVo>();

    //netty 通道组
    public static ChannelGroup NETTY_CHANNEL_GROUP = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    //appUser用户id和websocket 通道
    public static Map<Long , ChannelHandlerContext> USER_WEBSOCKET_CHANNEL = new HashMap<Long , ChannelHandlerContext>();

    //后台管理用户 id+ 设备编号和websocket 通道
    public static Map<String , ChannelHandlerContext> CONSOLE_USER_WEBSOCKET_CHANNEL = new HashMap<String , ChannelHandlerContext>();

    final  static  ExecutorService pool = Executors.newCachedThreadPool();

    /**
     * 新增通道
     * @param terminalNo 设备编号
     * @param channel  通道
     * @param serverIP 服务IP
     * @param serverPort 端口号
     * @return
     * @throws Exception
     */
    public static boolean addChannel(String terminalNo , ChannelHandlerContext channel ,CloudCabinetVo cloudCabinetVo,
                                     String serverIP , int serverPort , int serverHttpPort , String uuid)throws Exception{
//      try {
//          CLOUD_ABINET_CHANNEL.put(terminalNo , channel);
//          CLOUD_ABINET_CHANNEL_TERMINAL_NO.put(channel.channel().id() ,cloudCabinetVo );
//          String localIp = IPUtil.getMyIPLocal();
//          String nodeValue = "{serverIp:\"" + serverIP + "\",serverPort:" + serverPort
//                  +",serverLocalIp:\"" + localIp  + "\",serverHttPort:" +serverHttpPort
//                  + ",connectInfo:\"" + channel.channel().remoteAddress() + "\"}";
//          hdelCount( terminalNo ,  serverIP ,  uuid);//删除计数
//          RedisStatic.getRedisUtil().hset(RedisKey.HASH_IMSERVER_TERMINAL_NODE ,terminalNo , nodeValue);
//          RedisStatic.getRedisUtil().incr(RedisKey.INT_IM_SERVER_NODE_COUNT_PRE + serverIP + "_" + uuid,RedisKey.INT_IM_SERVER_NODE_VALID_TIME );
//          logger.error("注册成功p------------》uuid：{}，{}",uuid ,  channel.toString());
//            //发送mq 在线
//          String sendData = "{terminalNo:\"" + cloudCabinetVo.getTerminalNo() + "\",onlineStatus:1," +
//                    "onlineUuid:\"" + uuid + "\"," +
//                "apkVersion:\"" + cloudCabinetVo.getApkVersion() + "\"," +
//                "remark:\"" +  channel.channel().remoteAddress().toString() + "\"}";
//          NettyUtil.sendOnOffLineMQ( sendData);
//        }catch (Exception e){
//            logger.error("注册失败----------------》" + channel.toString() + ";redis-->" + RedisKey.INT_IM_SERVER_NODE_COUNT_PRE + serverIP + "_" + uuid);
//            e.printStackTrace();
//            logger.error(e.getMessage());
//            return false;
//        }
        return  true;
    }

    /**
     * 移除通道
     * @param channel
     * @param serverIP 服务器IP
     * @return
     */
    public static boolean removeChannel(ChannelHandlerContext channel ,  String serverIP , String uuid){
//        CloudCabinetVo cloudCabinetVo = CLOUD_ABINET_CHANNEL_TERMINAL_NO.get(channel.channel().id());
//        if(!Utility.isNullorEmpty(cloudCabinetVo)){
//            CLOUD_ABINET_CHANNEL_TERMINAL_NO.remove(channel.channel().id());
//            try {
//                //删除zookeeper
//                if(CLOUD_ABINET_CHANNEL.get(cloudCabinetVo.getTerminalNo()) == channel){//相同就删除
//                    CLOUD_ABINET_CHANNEL.remove(cloudCabinetVo.getTerminalNo());
//                    hdelCount(cloudCabinetVo.getTerminalNo() ,  serverIP ,  uuid);              //redis计数
//                    RedisStatic.getRedisUtil().hdel(RedisKey.HASH_IMSERVER_TERMINAL_NODE ,cloudCabinetVo.getTerminalNo());
//                    //发送mq 离线
//                    String sendData = "{terminalNo:\"" + cloudCabinetVo.getTerminalNo() +
//                            "\",onlineStatus:2," +
//                            "onlineUuid:\"" + uuid + "\"," +
//                            "apkVersion:\"" + cloudCabinetVo.getApkVersion() + "\"," +
//                            "remark:\"" +  channel.channel().remoteAddress().toString() + "\"}";
//                    NettyUtil.sendOnOffLineMQ( sendData);
//                }
//            }catch (Exception e){
//                logger.error(e.getMessage());
//            }
//            return  true;
//        }
        return false;
    }

    /**
     * redis 删除计数
     * @param terminalNo
     * @param serverIP
     * @param uuid
     */
    public static void hdelCount(String terminalNo , String serverIP , String uuid){
//        if( RedisStatic.getRedisUtil().hexists(RedisKey.HASH_IMSERVER_TERMINAL_NODE ,terminalNo )){
//            String countStr = RedisStatic.getRedisUtil().get(RedisKey.INT_IM_SERVER_NODE_COUNT_PRE + serverIP + "_" + uuid);//获取服务器连接客户端数量
//            int count =  Utility.parseInt(countStr);
//            if(count > 0){
//                RedisStatic.getRedisUtil().decr(RedisKey.INT_IM_SERVER_NODE_COUNT_PRE + serverIP + "_" + uuid ,RedisKey.INT_IM_SERVER_NODE_VALID_TIME );
//            }
//        }
    }


    /**
     * 添加用户websocket通道
     * @param userId
     * @param channel
     */
    public static void addUserWebsocketChannel(Long userId , ChannelHandlerContext channel ){
        USER_WEBSOCKET_CHANNEL.put(userId , channel);
    }

    /**
     * 移除用户websocket通道
     * @param channel
     */
    public static void removeUserWebsocketChannel(ChannelHandlerContext channel ){
        for(Long userId : USER_WEBSOCKET_CHANNEL.keySet()){
            if(channel == USER_WEBSOCKET_CHANNEL.get(userId)){
                USER_WEBSOCKET_CHANNEL.remove(userId);
                break;
            }
        }
    }



    /**
     * 添加console用户websocket通道
     * @param userId  用户Id
     * @param terminalNo 设备编号
     * @param channel
     */
    public static void addConsoleUserWebsocketChannel(Long userId , String terminalNo , ChannelHandlerContext channel ){
        CONSOLE_USER_WEBSOCKET_CHANNEL.put(userId + "_" + terminalNo , channel);
    }

    /**
     * 根据通道获取console用户
     * @param channel
     * @return
     */
    public static String getConsoleUserIdByChannel( ChannelHandlerContext channel ){
        for(  String userId_terminalNo : CONSOLE_USER_WEBSOCKET_CHANNEL.keySet()) {
            ChannelHandlerContext context = CONSOLE_USER_WEBSOCKET_CHANNEL.get(userId_terminalNo);
            if(channel == context ) return  userId_terminalNo;
        }
        return null;
    }
    /**
     * 移除console用户websocket通道
     * @param channel
     */
    public static void removeConsoleUserWebsocketChannel(ChannelHandlerContext channel ){
        for(String userId_terminalNo : CONSOLE_USER_WEBSOCKET_CHANNEL.keySet()){
            if(channel == CONSOLE_USER_WEBSOCKET_CHANNEL.get(userId_terminalNo)){
                CONSOLE_USER_WEBSOCKET_CHANNEL.remove(userId_terminalNo);
                break;
            }
        }
    }
}
