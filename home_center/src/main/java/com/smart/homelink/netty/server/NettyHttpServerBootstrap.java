package com.smart.homelink.netty.server;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * netty server
 * Created by think on 2018/3/28.
 */
public class NettyHttpServerBootstrap implements Runnable{
    Logger logger = LoggerFactory.getLogger(NettyHttpServerBootstrap.class);
    private int port;

    public NettyHttpServerBootstrap(int port) {
        this.port = port;
    }
    @Override
    public void run() {
        //1 第一个线程组 是用于接收Client端连接的
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //2 第二个线程组 是用于实际的业务处理操作的
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            //3 创建一个辅助类Bootstrap，就是对我们的Server进行一系列的配置
            ServerBootstrap bootStrap = new ServerBootstrap();

            bootStrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)		//设置tcp缓冲区
                .option(ChannelOption.SO_SNDBUF, 32*1024)	//设置发送缓冲大小
                .option(ChannelOption.SO_RCVBUF, 32*1024)	//这是接收缓冲大小
                .option(ChannelOption.TCP_NODELAY, true)// 不延迟，消息立即发送
                .childHandler(new NettyHttpServerInitializer( this.port));
            ChannelFuture channelFuture = bootStrap.bind(port).sync();
            channelFuture.channel().closeFuture().sync();
            //channelFuture2.channel().closeFuture().sync();     //可以绑定多个
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}

