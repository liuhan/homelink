package com.smart.homelink.netty.test;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.InputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by think on 2018/3/28.
 */
public class SocketClientRun implements Runnable{
    Socket socket;

    public SocketClientRun(Socket socket) {
        this.socket = socket;

    }

    @Override
    public void run() {
        try {
            byte[] datalen = new byte[13];
            InputStream is = socket.getInputStream();
            is.read(datalen);//读取前四个字节数据存放到datalen中
            byte[] origina1 = Arrays.copyOfRange(datalen, 0, 4);
            byte[] origina2 = Arrays.copyOfRange(datalen, 4, 8);
            byte[] origina3 = Arrays.copyOfRange(datalen, 8, 9);
            byte[] origina4 = Arrays.copyOfRange(datalen, 9, 13);

            int length1 = byteArrayToInt(origina1);//将字节数组转换为int型
            int length2 = byteArrayToInt(origina2);//将字节数组转换为int型

            int length3 = byteArrayToInt2(origina3);//将字节数组转换为int型
            int length4 = byteArrayToInt(origina4);//将字节数组转换为int型
            byte[] data = new byte[length4];
            is.read(data);
            String recvMsg = new String(data);//将获得数据转为字符串类型
            System.out.println(recvMsg);
            //is.close();
            SocketClientWriteRun.send();
        }catch (Exception e){

        }

    }


    public static int byteArrayToInt2(byte[] b){
        return  b[0]&0xFF;
    }
    public static int byteArrayToInt(byte[] b){
        return b[3]&0xFF | (b[2]&0xFF) << 8 | (b[1]&0xFF) << 16 | (b[0]&0xFF) << 24;
    }
}
