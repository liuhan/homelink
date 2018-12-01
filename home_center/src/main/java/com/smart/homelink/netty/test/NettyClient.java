package com.smart.homelink.netty.test;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by think on 2018/3/28.
 */
public class NettyClient implements Runnable{
    private String host;
    private int port;
    static  Bootstrap bootStrap ;
    public NettyClient(String host, int port ,   Bootstrap bootStrap ) {
        this.host = host;
        this.port = port;
        this.bootStrap = bootStrap;
    }


    public static void setB( Bootstrap bootStrap ){
        bootStrap = bootStrap;
    }


    private ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
    EventLoopGroup workerGroup = new NioEventLoopGroup();
    /**
     * 启动
     */
    public void run() {

        try {
            Bootstrap bootStrap = new Bootstrap();
            bootStrap.group(workerGroup)
                    .channel(NioSocketChannel.class)
                    //.option(ChannelOption.SO_KEEPALIVE, true)//长连接
                    .option(ChannelOption.SO_BACKLOG, 1024)		//设置tcp缓冲区
                    .option(ChannelOption.SO_SNDBUF, 32*1024)	//设置发送缓冲大小
                    .option(ChannelOption.SO_RCVBUF, 32*1024)	//这是接收缓冲大
                    //.option(ChannelOption.TCP_NODELAY, true)//只延时发送
                   .handler(new NettyClientInitializer());
            ChannelFuture f = bootStrap.connect(host, port).addListener(new ChannelFutureListener(){
                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    if (!channelFuture.isSuccess()) {
                        System.out.println("连接不成功");
                    }
                }
            }).sync();

            for (int i = 0 ;i<1000 ; i ++){
                String s = "42323446564";
                ChannelFuture channelFuture = f.channel().writeAndFlush(s);
              //  Thread.sleep(2000);
               // ChannelFuture channelFuture2 =  f.channel().writeAndFlush(nettyMessage );
            }

            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {

            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();

            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try{
                        TimeUnit.SECONDS.sleep(2);
                        try {
                            NettyClient nettyClient = new NettyClient("192.168.1.200" , 9000 ,null);
                          //  nettyClient.run();
                            new Thread(nettyClient).start();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }catch (InterruptedException r){

                    }

                }
            });
        }


    }
}
