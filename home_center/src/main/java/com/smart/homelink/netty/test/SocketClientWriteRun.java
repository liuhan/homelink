package com.smart.homelink.netty.test;

import java.io.OutputStream;

/**
 * Created by think on 2018/3/28.
 */
public class SocketClientWriteRun implements Runnable{
    static  OutputStream outputStream;
    public SocketClientWriteRun(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    @Override
    public void run() {
        try {
        String msg = "3434阿斯顿发射点飞洒地方爱的色放法国的发射点风格";

        byte[] b = msg.getBytes("utf-8");
            Integer x = Integer.parseInt("0760",16);

            Integer x2 = Integer.parseInt("0660",16);
            Integer x3 = Integer.parseInt("21",16);
            byte[] ee =  intToBytes(x.intValue(), 4);
            byte[] ee2 =  intToBytes(x2.intValue(), 4);
            byte[] ee3 =  intToBytes(x3.intValue(), 1);
            byte[] ee4=  intToBytes(b.length, 4);
            byte[] eee =  new byte[ee.length + ee2.length + ee3.length+ ee4.length + b.length];
            System.arraycopy(ee, 0, eee,0, ee.length);
            System.arraycopy(ee2, 0, eee,ee.length, ee2.length);
            System.arraycopy(ee3, 0, eee,ee.length + ee2.length , ee3.length);
            System.arraycopy(ee4, 0, eee,ee.length + ee2.length+ ee3.length , ee4.length);

            System.arraycopy(b, 0, eee,ee.length + ee2.length + ee3.length + ee4.length, b.length);

            outputStream.write(eee );
      /*      System.arraycopy(b2, 0, ee,0, b2.length);
            System.arraycopy(b, 0, ee,  b2.length, b.length) ;
        outputStream.write(ee);*/

           // outputStream.write(b);
       // outputStream.write(0x0660);
        //outputStream.write(0xFF);
        //outputStream.write(b.length);
      //  outputStream.write(b);
            outputStream.flush();
        }catch (Exception e){

        }

    }


    public  static void send(){
        try {
        String msg = "3434阿斯顿发射点飞洒地方爱的色放法国的发射点风格";

        byte[] b = msg.getBytes("utf-8");
        Integer x = Integer.parseInt("0760",16);

        Integer x2 = Integer.parseInt("0660",16);
        Integer x3 = Integer.parseInt("21",16);
        byte[] ee =  intToBytes(x.intValue(), 4);
        byte[] ee2 =  intToBytes(x2.intValue(), 4);
        byte[] ee3 =  intToBytes(x3.intValue(), 1);
        byte[] ee4=  intToBytes(b.length, 4);
        byte[] eee =  new byte[ee.length + ee2.length + ee3.length+ ee4.length + b.length];
        System.arraycopy(ee, 0, eee,0, ee.length);
        System.arraycopy(ee2, 0, eee,ee.length, ee2.length);
        System.arraycopy(ee3, 0, eee,ee.length + ee2.length , ee3.length);
        System.arraycopy(ee4, 0, eee,ee.length + ee2.length+ ee3.length , ee4.length);

        System.arraycopy(b, 0, eee,ee.length + ee2.length + ee3.length + ee4.length, b.length);

        outputStream.write(eee );
      /*      System.arraycopy(b2, 0, ee,0, b2.length);
            System.arraycopy(b, 0, ee,  b2.length, b.length) ;
        outputStream.write(ee);*/

        // outputStream.write(b);
        // outputStream.write(0x0660);
        //outputStream.write(0xFF);
        //outputStream.write(b.length);
        //  outputStream.write(b);
        outputStream.flush();
    }catch (Exception e){

    }
    }

    /**
     * 把16进制字符串转换成字节数组
     * @param hexString
     * @return byte[]
     */
    public static byte[] hexStringToByte(String hexString) {
        int len = (hexString.length() / 2);
        byte[] result = new byte[len];
        char[] achar = hexString.toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
        }
        return result;
    }
    private static int toByte(char c) {
        byte b = (byte) "0123456789ABCDEF".indexOf(c);
        return b;
    }
    /**
     * Transform integer array to byte
     * @param source
     *            the source need to be transformed
     * @param length
     *            the length of byte array
     * @return b the length of byte array b
     */
    public static byte[] intToBytes(int source, int length) {
        byte[] b = new byte[length];
        for (int i = 0; i < length; i++) {
            b[i] = (byte) (source >> 8 * (length - i - 1) & 0xFF);
        }
        return b;
    }
}
