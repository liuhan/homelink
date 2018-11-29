package com.smart.util.netty.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kelent.secucity.SignUtil;
import com.kelent.type.CommonReturnMessageType;
import com.kelent.util.HttpRequestClientUtil;
import com.kelent.util.Utility;
import com.kelent.util.netty.NettyIMMessageType;
import com.kelent.util.redis.RedisKey;
import com.kelent.util.redis.RedisStatic;
import com.kelent.util.zookeeper.ZKStatic;
import com.kelent.util.zookeeper.ZkKeys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Author : syl
 * datetime ：2018/4/27.
 */
public class IMHttpRequestUtil {

    public static final Logger logger = LoggerFactory.getLogger(IMHttpRequestUtil.class);
    /**
     * 开门
     * @param terminalNo
     * @return
     * @throws Exception
     */
    public static CommonReturnMessageType openDoor(Long userId ,String appid , String terminalNo)throws Exception{
        Map paramMap = new HashMap();
        Map map = SignUtil.getSignMap();
        map.put("terminalNo" ,terminalNo );
        map.put("appid" ,appid );
        SignUtil.getSign(map);
        paramMap.put("code" , NettyIMMessageType.SYS_APPLICATION_CLIENT_OPEN_DOOR);
        paramMap.put("terminalNo" , terminalNo);
        paramMap.put("userId" , userId);
        paramMap.put("data" , JSON.toJSONString(map));
       return sendData(  paramMap ,terminalNo);
    }


    /**
     * 订单支付信息
     * @param userId
     * @param terminalNo
     * @param requestMap  status:支付状态 status 0-开门 1-已关门 2-支付成功 3-支付失败 20-支付金额为0    orderNo:订单编号
     * @return
     * @throws Exception
     */
    public static CommonReturnMessageType orderPayStatus(Long userId  , String terminalNo ,Map requestMap)throws Exception{
        Map paramMap = new HashMap();
        Map map = SignUtil.getSignMap();
        map.put("terminalNo" ,terminalNo );
        map.putAll(requestMap);
        SignUtil.getSign(map);
        paramMap.put("code" , NettyIMMessageType.SYS_APPLICATION_CLIENT_ORDER_PAY_STATUS);
        paramMap.put("terminalNo" , terminalNo);
        paramMap.put("userId" , userId);
        paramMap.put("data" , JSON.toJSONString(map));
        logger.info("terminalNo----------------->" + terminalNo +",,userId--------"+ userId + " -- map{}" , requestMap);
        return sendData(  paramMap ,terminalNo);
    }

    /**
     * 发送数据
     * @param paramMap
     * @return
     */
    public static CommonReturnMessageType sendData(Map<String, Object>  paramMap , String terminalNo){
        try{

            String  terminalNoServer = RedisStatic.getRedisUtil().hget(RedisKey.HASH_IMSERVER_TERMINAL_NODE ,terminalNo);
            //  String nodeValue = "{serverIp:\"" + serverIP + "\",serverPort:" + serverPort +",serverLocalIp:\""  + localIp  + "\",serverHttPort:" +serverHttpPort + "}";
           // String terminalNoServer = new String(ZKStatic.getZkUtil().getClient().getData().forPath(ZkKeys.CLOUD_CABINET_TERMINAL_SERVERINFO + "/" + terminalNo));
            if(Utility.isNullorEmpty(terminalNoServer)) return  CommonReturnMessageType.IM_HTTP_REQUEST_ERROR;
            Map<String ,Object > map = (Map<String, Object>) JSON.parse(terminalNoServer);
            if(Utility.isNullorEmpty(map.get("serverLocalIp")) || Utility.isNullorEmpty(map.get("serverHttPort")) ){
                return CommonReturnMessageType.IM_HTTP_REQUEST_ERROR;
            }
            JSONObject jsonObject = HttpRequestClientUtil.sendPost("http://" + map.get("serverLocalIp") + ":" + map.get("serverHttPort") ,paramMap );
            CommonReturnMessageType commonReturnMessageType = CommonReturnMessageType.get(jsonObject.getString("code"));
            commonReturnMessageType.setData(map);
            return  commonReturnMessageType;
        }catch (Exception e){
            logger.info(e.getMessage());
            return CommonReturnMessageType.IM_HTTP_REQUEST_ERROR;
        }
    }
}
