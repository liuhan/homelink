package com.kelent.netty.test;

import com.kelent.util.IPUtil;
import com.kelent.util.Utility;
import com.kelent.util.springboot.ApplicationProperties;
import com.kelent.util.zookeeper.ZkKeys;
import com.kelent.util.zookeeper.ZkUtil;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.zookeeper.CreateMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * netty server
 * Created by think on 2018/3/28.
 */
public class NettyServerBootstrap implements Runnable{
    Logger logger = LoggerFactory.getLogger(NettyServerBootstrap.class);
    private int port;
    private int httpPort;//
    private String networkIp;//
    private String uuid;//唯一标识
    private  ZkUtil zkUtil;

    public NettyServerBootstrap(int port ,int nettyHttpPort,String networkIp ) {

        this.port = port;
        this.httpPort = nettyHttpPort;
        this.networkIp = networkIp;
        this.zkUtil = zkUtil;
        this.uuid = Utility.getUuid();
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
              //  .option(ChannelOption.SO_KEEPALIVE, true)  //开启长连接
                .option(ChannelOption.SO_BACKLOG, 1024)		//设置tcp缓冲区
                .option(ChannelOption.SO_SNDBUF, 32*1024)	//设置发送缓冲大小
                .option(ChannelOption.SO_RCVBUF, 32*1024)	//这是接收缓冲大小
            //    .option(ChannelOption.TCP_NODELAY, true)// 不延迟，消息立即发送
                .childHandler(new NettyServerInitializer(this.networkIp , this.port ,this.httpPort , this.uuid));
            ChannelFuture channelFuture = bootStrap.bind(port).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }


}

