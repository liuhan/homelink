package com.smart.util.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by syl on 2017/12/26.
 */
public class MD5 {

    //生成MD5
    public static String getMD5(String message) {
        return getMD5(  message , "UTF-8");
    }

    //生成MD5
    public static String getMD5(String message , String charName) {
        String md5 = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");  // 创建一个md5算法对象
            byte[] messageByte = message.getBytes(charName);
            byte[] md5Byte = md.digest(messageByte);              // 获得MD5字节数组,16*8=128位
            md5 = bytesToHex(md5Byte);                            // 转换为16进制字符串
        } catch (Exception e) {
            e.printStackTrace();
        }
        return md5;
    }

    /**
     *     二进制转十六进制
     */
    public static String bytesToHex(byte[] bytes) {
        StringBuffer hexStr = new StringBuffer();
        int num;
        for (int i = 0; i < bytes.length; i++) {
            num = bytes[i];
            if(num < 0) {
                num += 256;
            }
            if(num < 16){
                hexStr.append("0");
            }
            hexStr.append(Integer.toHexString(num));
        }
        return hexStr.toString().toUpperCase();
    }
    /**
     * md5 加密 反转
     * @param message
     * @return
     */
    public static String getMD5Reverse(String message) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] result = digest.digest(message.getBytes());
            StringBuffer sb = new StringBuffer();
            sb.append(getMD5(message));
            //规则 :加密的字符取前10位拼接在加密字符后 再反转
            return sb.append(sb.substring(0, 10)).reverse().toString();
        } catch (NoSuchAlgorithmException ex) {
            return message;
        }
    }
}
