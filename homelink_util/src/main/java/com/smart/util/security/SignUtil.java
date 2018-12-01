package com.smart.util.security;

import com.smart.util.Utility;
import org.apache.commons.codec.EncoderException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.dsig.SignatureMethod;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
/**
 * 签名方法
 * @Author: lh 
 * @Date: 2018/4/5 19:09
 * @ClassName: SignUtil
 */
public class SignUtil {
    private static Logger logger = LoggerFactory.getLogger(SignUtil.class);
    private static final String ENCODE_TYPE = "UTF-8";
    public static final String signKey = "sign";
    public static final String timestampKey = "timestamp";
    public static final String nonceKey = "nonce";
    public static final Long expireTime = 10 * 60 * 1000l;//10分钟有效

    //云柜设备签名key
    public static final String terminal_key = "kelent_terminal_key_123_!@#";

    /**
     * 验证签名
     * @param request
     * @param key
     * @return
     */
    public static boolean validateSign(HttpServletRequest request , String key ) {
        Map<String , String[]> map = request.getParameterMap();
        Map<String , String> sign = new HashMap<>();
        for (String k : map.keySet()) {
            String[] vals = map.get(k);
            if (vals != null && vals.length > 0) {
                sign.put(k ,vals[0]);
            }
        }
        if (!SignUtil.validateSign4Redis(key , sign)) {
            return false;
        }
        return true;
    }
    /**
     * 设备验证参数签名
     * @param params
     * @return
     */
    public static boolean validateSign(  Map<String, String> params) {
        return validateSign(terminal_key , params);
    }

    /**
     * 验证参数签名
     * @param key
     * @param params
     * @return
     */
    public static boolean validateSign( String key , Map<String, String> params) {
        try {
            String timestamp = params.get(timestampKey);
            if (timestamp == null || "".equals(timestamp)) {
                return false;
            }
            long now = System.currentTimeMillis();
            //N分钟过期
            if (now > Long.parseLong(timestamp) + expireTime) {
                return false;
            }
            String nonce = params.get(nonceKey);
            //取得sign
            String signValue = params.get(signKey);
            //先移出
            params.remove(signKey);

            String signType = params.get("signType");
            //设置rediskey
            String sign = "";
            if ("MD5".equals(signType)) {
                sign = getMd5Sign(key , params);
            } else {
                sign = getSign(key , params);
            }


            if (sign == null || "".equals(sign)) return false;

            if (sign.equals(signValue)) {
                return true;
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            logger.error("{}"  , e);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            logger.error("{}"  , e);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
            logger.error("{}"  , e);
        } catch (EncoderException e) {
            e.printStackTrace();
            logger.error("{}"  , e);
        }
        return false;
    }


    /**
     * 验证参数签名
     * @param key
     * @param params
     * @return
     */
    public static boolean validateSign4Redis( String key , Map<String, String> params) {
        try {
            String timestamp = params.get(timestampKey);
            if (timestamp == null || "".equals(timestamp)) {
                return false;
            }
            long now = System.currentTimeMillis();
            //5分钟过期
            if (now > Long.parseLong(timestamp) + expireTime) {
                return false;
            }

            String nonce = params.get(nonceKey);
            //redis存在，说明已经用过了
           /* String redisKey = RedisKey.STRING_NONCE_PRE + nonce;
            if (RedisStatic.getRedisUtil().exists(redisKey)) {
                return false;
            }*/
            //取得sign
            String signValue = params.get(signKey);
            //先移出
            params.remove(signKey);

            //设置rediskey
            // RedisStatic.getRedisUtil().setex(redisKey ,   10 * 60  , "1" );
            String signType = params.get("signType");
            //设置rediskey
            String sign = "";
            if ("MD5".equals(signType)) {
                sign = getMd5Sign(key , params);
            } else {
                sign = getSign(key , params);
            }

            if (sign == null || "".equals(sign)) return false;

            if (sign.equals(signValue)) {
                return true;
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            logger.error("{}"  , e);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            logger.error("{}"  , e);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
            logger.error("{}"  , e);
        } catch (EncoderException e) {
            e.printStackTrace();
            logger.error("{}"  , e);
        }
        return false;
    }
    /**
     * 获取标准的签名MAp
     * @return
     */
    public static Map<String , String > getSignMap(){
        Map<String , String > map = new HashMap<String , String >();
        map.put(timestampKey , System.currentTimeMillis() + "");
        map.put(nonceKey , Utility.getUuid());
        return  map;
    }

    /**
     * 取得签名 设备apk
     * @param params
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    public static String getSign( Map<String, String> params) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
        return getSign(terminal_key ,  params);
    }

    /**
     * 取得MD5签名
     * @param key
     * @param params
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    public static String getMd5Sign(String key , Map<String, String> params) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, EncoderException {
        logger.info("请求参数:{}" ,params);
        List<String> sortedKeys = new ArrayList<String>(params.keySet());
        Collections.sort(sortedKeys);

        final String SEPARATOR = "&";
        final String EQUAL = "=";
        StringBuilder stringToSign = new StringBuilder();
        StringBuilder canonicalizedQueryString = new StringBuilder();
        for (String k : sortedKeys) {
            String value = params.get(k);
            canonicalizedQueryString.append(SEPARATOR).append(percentEncode(k)).append(EQUAL).append(percentEncode(value));
        }
        canonicalizedQueryString.append(SEPARATOR).append(percentEncode("key")).append(EQUAL).append(percentEncode(key));
        stringToSign.append(percentEncode(canonicalizedQueryString.toString().substring(1)));
        return MD5.getMD5(stringToSign.toString());
    }
        /**
         * 取得签名
         * @param key
         * @param params
         * @return
         * @throws UnsupportedEncodingException
         * @throws NoSuchAlgorithmException
         * @throws InvalidKeyException
         */
    public static String getSign(String key , Map<String, String> params) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
        logger.info("请求参数:{}" ,params);
        List<String> sortedKeys = new ArrayList<String>(params.keySet());
        Collections.sort(sortedKeys);

        final String SEPARATOR = "&";
        final String EQUAL = "=";
        StringBuilder stringToSign = new StringBuilder();
        StringBuilder canonicalizedQueryString = new StringBuilder();
        for (String k : sortedKeys) {
            String value = params.get(k);
            canonicalizedQueryString.append(SEPARATOR).append(percentEncode(k)).append(EQUAL).append(percentEncode(value));
        }

        stringToSign.append(percentEncode(canonicalizedQueryString.toString().substring(1)));
        final String ALGORITHM = "HmacSHA1";

        SecretKey secretKey = new SecretKeySpec((key).getBytes(ENCODE_TYPE), SignatureMethod.HMAC_SHA1);
        Mac mac = Mac.getInstance(ALGORITHM);
        mac.init(secretKey);

        String signKeyStr = URLEncoder.encode(new String(new org.apache.commons.codec.binary.Base64().encode(mac.doFinal(stringToSign.toString().getBytes(ENCODE_TYPE))), ENCODE_TYPE), ENCODE_TYPE);
        params.put(signKey , signKeyStr);
        return signKeyStr;
    }

    private static String percentEncode(String value)  {
        if (value == null) return "";
        try {
            return URLEncoder.encode(value, ENCODE_TYPE).replace("+", "%20").replace("*", "%2A").replace("%7E", "~");
        } catch (Exception e) {
        //不可能发生的异常
        }
        return "";
    }
}
