package com.smart.homelink.netty.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.smart.homelink.type.RetMsgType;
import com.smart.util.Utility;
import com.smart.util.redis.RedisKey;
import com.smart.util.redis.RedisStatic;
import com.smart.util.security.SignUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Author : syl
 * datetime ：2018/4/27.
 */
public class IMHttpRequestConsoleUtil {

    public static final Logger logger = LoggerFactory.getLogger(IMHttpRequestConsoleUtil.class);


    /**
     * 批量设置音量
     * @param terminalNo
     * @param requestMap firdgeId
     * @return
     *
     * @throws Exception
     */
    public static RetMsgType openDoor(Long userId , String terminalNo , Map<String , String > requestMap)throws Exception{
        if(!Utility.isNullorEmpty(terminalNo)){
            Map paramMap = new HashMap();
            Map map = SignUtil.getSignMap();
            map.put("terminalNo" ,terminalNo );
            map.putAll(requestMap);
            SignUtil.getSign(map);
            paramMap.put("code" , NettyIMMessageType.CLOUD_ABINET_SERVER_APP_OPEN_DOOR);
            paramMap.put("terminalNo" , terminalNo);
            paramMap.put("userId" , userId );
            paramMap.put("data" , JSON.toJSONString(map));
            if (RedisStatic.getRedisUtil().exists(RedisKey.STRING_CLOUD_CABINET_TERMINAL_USE_STATUS +terminalNo )) {
               return RetMsgType.IM_HTTP_REQUEST_CLOUD_CABINET_TRADING;
            }
            RetMsgType RetMsgType =  sendData(  paramMap ,terminalNo);
            if (RetMsgType.SUCCESS.getCode().equals(RetMsgType.getCode())) {
                //锁住设备不让多个用户开门
                RedisStatic.getRedisUtil().setex(RedisKey.STRING_CLOUD_CABINET_TERMINAL_USE_STATUS + terminalNo,
                        RedisKey.CLOUD_CABINET_TERMINAL_USE_STATUS_VALID_TIME,
                        "{userType:\"adminUser\", userId:" + userId+ "}");
            }
            return RetMsgType;
        }
        return RetMsgType.IM_HTTP_REQUEST_APP_OPENDOOR_ERROR;
    }

    /**
     * 批量设置音量
     * @param terminalNos
     * @param requestMap 警告提示音 alarmVolume、播放音量 play_volume、亮度 brightness

     * @return
     * @throws Exception
     */
    public static RetMsgType batchSetVolume(Long userId , String terminalNos , Map<String , String > requestMap)throws Exception{
        StringTokenizer stringTokenizer = new StringTokenizer(terminalNos , ",");
        while (stringTokenizer.hasMoreElements()){
            String terminalNo = stringTokenizer.nextToken();
            if(!Utility.isNullorEmpty(terminalNo)){
                Map paramMap = new HashMap();
                Map map = SignUtil.getSignMap();
                map.put("terminalNo" ,terminalNo );
                map.putAll(requestMap);
                SignUtil.getSign(map);
                paramMap.put("code" , NettyIMMessageType.CLOUD_ABINET_SERVER_ALARMANDADVERTAND_VOLUME_SCREEN_BRIGHTNESS);
                paramMap.put("terminalNo" , terminalNo);
                paramMap.put("userId" , userId);
                paramMap.put("data" , JSON.toJSONString(map));
                sendData(  paramMap ,terminalNo);
            }
        }
        return RetMsgType.SUCCESS;
    }

    /**
     * 重启
     * @param terminalNos
     * @return
     * @throws Exception
     */
    public static RetMsgType batchReboot(Long userId , String terminalNos )throws Exception{
        StringTokenizer stringTokenizer = new StringTokenizer(terminalNos , ",");
        while (stringTokenizer.hasMoreElements()){
            String terminalNo = stringTokenizer.nextToken();
            if(!Utility.isNullorEmpty(terminalNo)){
                Map paramMap = new HashMap();
                Map map = SignUtil.getSignMap();
                map.put("terminalNo" ,terminalNo );
                SignUtil.getSign(map);
                paramMap.put("code" , NettyIMMessageType.CLOUD_ABINET_SERVER_REBOT);
                paramMap.put("terminalNo" , terminalNo);
                paramMap.put("userId" , userId);
                paramMap.put("data" , JSON.toJSONString(map));
                sendData(  paramMap ,terminalNo);
            }
        }
        return RetMsgType.SUCCESS;
    }

    /**
     * 批量升级
     * @param terminalNos
     * @param requestMap version 版本、downAddress下载地址
     * @return
     * @throws Exception
     */
    public static RetMsgType batchUAKPpgrade(Long userId , String terminalNos ,Map<String , String > requestMap)throws Exception{
        StringTokenizer stringTokenizer = new StringTokenizer(terminalNos , ",");
        while (stringTokenizer.hasMoreElements()){
            String terminalNo = stringTokenizer.nextToken();
            if(!Utility.isNullorEmpty(terminalNo)){
                Map paramMap = new HashMap();
                Map map = SignUtil.getSignMap();
                map.put("terminalNo" ,terminalNo );
                map.putAll(requestMap);
                SignUtil.getSign(map);
                paramMap.put("code" , NettyIMMessageType.CLOUD_ABINET_SERVER_AKP_UPDATE);
                paramMap.put("terminalNo" , terminalNo);
                paramMap.put("userId" , userId);
                paramMap.put("data" , JSON.toJSONString(map));
                sendData(  paramMap ,terminalNo);
            }
        }
        return RetMsgType.SUCCESS;
    }


    /**
     * 批量更新密码
     * @param terminalNos
     * @param requestMap password 密码
     * @return
     * @throws Exception
     */
    public static RetMsgType batchSetPassword(Long userId , String terminalNos ,Map requestMap)throws Exception{
        StringTokenizer stringTokenizer = new StringTokenizer(terminalNos , ",");
        while (stringTokenizer.hasMoreElements()){
            String terminalNo = stringTokenizer.nextToken();
            if(!Utility.isNullorEmpty(terminalNo)){
                Map paramMap = new HashMap();
                Map map = SignUtil.getSignMap();
                map.put("terminalNo" ,terminalNo );
                map.putAll(requestMap);
                SignUtil.getSign(map);
                paramMap.put("code" , NettyIMMessageType.CLOUD_ABINET_SERVER_UPDATE_PASSWORD);
                paramMap.put("terminalNo" , terminalNo);
                paramMap.put("userId" , userId);
                paramMap.put("data" , JSON.toJSONString(map));
                sendData(  paramMap ,terminalNo);
            }
        }
        return RetMsgType.SUCCESS;
    }

    /**
     * 批量更新开关机时间
     * @param terminalNos
     * @param requestMap password 密码
     * @return
     * @throws Exception
     */
    public static RetMsgType batchSetOnOffTime(Long userId , String terminalNos ,Map<String , String > requestMap)throws Exception{
        StringTokenizer stringTokenizer = new StringTokenizer(terminalNos , ",");
        while (stringTokenizer.hasMoreElements()){
            String terminalNo = stringTokenizer.nextToken();
            if(!Utility.isNullorEmpty(terminalNo)){
                Map paramMap = new HashMap();
                Map map = SignUtil.getSignMap();
                map.put("terminalNo" ,terminalNo );
                map.putAll(requestMap);
                SignUtil.getSign(map);
                paramMap.put("code" , NettyIMMessageType.CLOUD_ABINET_SERVER_ON_OFF);
                paramMap.put("terminalNo" , terminalNo);
                paramMap.put("userId" , userId);
                paramMap.put("data" , JSON.toJSONString(map));
                sendData(  paramMap ,terminalNo);
            }
        }
        return RetMsgType.SUCCESS;
    }

    /**
     * 更新二维码
     * @param terminalNos
     * @param requestMap password 密码
     * @return
     * @throws Exception
     */
    public static RetMsgType updateQrCode(Long userId , String terminalNos ,Map<String , String > requestMap)throws Exception{
        StringTokenizer stringTokenizer = new StringTokenizer(terminalNos , ",");
        while (stringTokenizer.hasMoreElements()){
            String terminalNo = stringTokenizer.nextToken();
            if(!Utility.isNullorEmpty(terminalNo)){
                Map paramMap = new HashMap();
                Map map = SignUtil.getSignMap();
                map.put("terminalNo" ,terminalNo );
                map.putAll(requestMap);
                SignUtil.getSign(map);
                paramMap.put("code" , NettyIMMessageType.CLOUD_ABINET_SERVER_UPDATE_QRCODE);
                paramMap.put("terminalNo" , terminalNo);
                paramMap.put("userId" , userId);
                paramMap.put("data" , JSON.toJSONString(map));
                sendData(  paramMap ,terminalNo);
            }
        }
        return RetMsgType.SUCCESS;
    }



    /**
     * 更新云柜商品表
     * @return
     * @throws Exception
     */
    public static RetMsgType updateGoodsTable(Long userId , String terminalNo )throws Exception{
        if(!Utility.isNullorEmpty(terminalNo)){
            Map paramMap = new HashMap();
            Map map = SignUtil.getSignMap();
            map.put("terminalNo" ,terminalNo );
            SignUtil.getSign(map);
            paramMap.put("code" , NettyIMMessageType.CLOUD_ABINET_SERVER_UPDATE_GOODS_TABLE);
            paramMap.put("terminalNo" , terminalNo);
            paramMap.put("userId" , userId);
            paramMap.put("data" , JSON.toJSONString(map));
            return  sendData(  paramMap ,terminalNo);
        }
        return RetMsgType.SUCCESS;
    }


    /**
     * 更新节目
     * @return
     * @throws Exception
     */
    public static RetMsgType updateProgram(Long userId , String terminalNo , String id )throws Exception {
        if (!Utility.isNullorEmpty(terminalNo)) {
            Map paramMap = new HashMap();
            Map map = SignUtil.getSignMap();
            map.put("terminalNo", terminalNo);
            map.put("publishId", id);
            SignUtil.getSign(map);
            paramMap.put("code", NettyIMMessageType.CLOUD_ABINET_SERVER_ADVERT);
            paramMap.put("terminalNo", terminalNo);
            paramMap.put("userId", userId);
            paramMap.put("data", JSON.toJSONString(map));
            return sendData(paramMap, terminalNo);
        }
        return RetMsgType.SUCCESS;
    }


    /**
     * 批量重量警告容错范围
     * @param userId
     * @param weight
     * @return
     * @throws Exception
     */
    public static RetMsgType updateAllWeightWarningErrorRange(Long userId  , String weight )throws Exception {
        Map<String , String > map = RedisStatic.getRedisUtil().hmgetAll(RedisKey.HASH_IMSERVER_TERMINAL_NODE);
        for (String terminalNo : map.keySet()){
            updateWeightWarningErrorRange(userId ,terminalNo , weight ,  map.get(terminalNo));
        }
        return RetMsgType.SUCCESS;
    }
        /**
         * 云柜设备重量警告容错范围g
         * @param userId
         * @param terminalNo
         * @param weight 重量
         * @return
         * @throws Exception
         */
    public static RetMsgType updateWeightWarningErrorRange(Long userId , String terminalNo , String weight , String terminalNoServer)throws Exception {
        if (!Utility.isNullorEmpty(terminalNo)) {
            Map paramMap = new HashMap();
            Map map = SignUtil.getSignMap();
            map.put("terminalNo", terminalNo);
            map.put("weight", weight);
            SignUtil.getSign(map);
            paramMap.put("code", NettyIMMessageType.CLOUD_ABINET_SERVER_UPDATE_WEIGHT_WARNING_ERROR_RANGE);
            paramMap.put("terminalNo", terminalNo);
            paramMap.put("userId", userId);
            paramMap.put("data", JSON.toJSONString(map));
            return sendData(paramMap, terminalNo , terminalNoServer);
        }
        return RetMsgType.SUCCESS;
    }

    /**
     * 批量重量每层容错范围
     * @param userId
     * @param weight
     * @return
     * @throws Exception
     */
    public static RetMsgType updateAllWeightLayerErrorRange(Long userId  , String weight )throws Exception {
        Map<String , String > map = RedisStatic.getRedisUtil().hmgetAll(RedisKey.HASH_IMSERVER_TERMINAL_NODE);
        for (String terminalNo : map.keySet()){
            updateWeightLayerErrorRange(userId ,terminalNo , weight ,  map.get(terminalNo));
        }
        return RetMsgType.SUCCESS;
    }
    /**
     *  云柜设备重量每层容错范围g
     * @param userId
     * @param terminalNo
     * @param weight 重量
     * @return
     * @throws Exception
     */
    public static RetMsgType updateWeightLayerErrorRange(Long userId , String terminalNo , String weight , String  terminalNoServer )throws Exception {
        if (!Utility.isNullorEmpty(terminalNo)) {
            Map paramMap = new HashMap();
            Map map = SignUtil.getSignMap();
            map.put("terminalNo", terminalNo);
            map.put("weight", weight);
            SignUtil.getSign(map);
            paramMap.put("code", NettyIMMessageType.CLOUD_ABINET_SERVER_UPDATE_WEIGHT_LAYER_ERROR_RANGE);
            paramMap.put("terminalNo", terminalNo);
            paramMap.put("userId", userId);
            paramMap.put("data", JSON.toJSONString(map));
            return sendData(paramMap, terminalNo , terminalNoServer);
        }
        return RetMsgType.SUCCESS;
    }

    /**
     * 发送数据
     * @param paramMap
     * @return
     */
    public static RetMsgType sendData(Map<String, Object>  paramMap , String terminalNo){
        try{
            String  terminalNoServer =  getTerminalInfo( terminalNo);
            return   sendData( paramMap ,  terminalNo , terminalNoServer);
        }catch (Exception e){
            logger.error(e.getMessage());
            return RetMsgType.IM_HTTP_REQUEST_ERROR;
        }
    }

    public static  RetMsgType sendData(Map<String, Object>  paramMap , String terminalNo ,String terminalNoServer)throws Exception{
        //  String nodeValue = "{serverIp:\"" + serverIP + "\",serverPort:" + serverPort +",serverLocalIp:\""  + localIp  + "\",serverHttPort:" +serverHttpPort + "}";
        if(Utility.isNullorEmpty(terminalNoServer)) return RetMsgType.IM_HTTP_REQUEST_ERROR;
        Map<String ,Object > map = (Map<String, Object>) JSON.parse(terminalNoServer);
        if(Utility.isNullorEmpty(map.get("serverLocalIp")) || Utility.isNullorEmpty(map.get("serverHttPort")) ){
            return RetMsgType.IM_HTTP_REQUEST_ERROR;
        }

        JSONObject jsonObject = HttpRequestClientUtil.sendPost("http://" + map.get("serverLocalIp") + ":" + map.get("serverHttPort") ,paramMap );
        return RetMsgType.get(jsonObject.getString("code"));
    }

    /**
     * 获取设备连接信息
     * @param terminalNo
     * @return
     */
    public static String  getTerminalInfo(String terminalNo){
        String  terminalNoServer = RedisStatic.getRedisUtil().hget(RedisKey.HASH_IMSERVER_TERMINAL_NODE ,terminalNo);
        return terminalNoServer;
    }
}
