package com.kelent.netty.server;


import com.smart.util.IPUtil;
import com.smart.util.Utility;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.apache.zookeeper.CreateMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;
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

    public NettyServerBootstrap(int port ,int nettyHttpPort,String networkIp ) {

        this.port = port;
        this.httpPort = nettyHttpPort;
        this.networkIp = networkIp;
        this.uuid = UUID.randomUUID().toString().replace("-" ,"");
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

            //ChannelFuture channelFuture2 = bootStrap.bind(port2).sync();     //可以绑定多个
            if(channelFuture.isSuccess()){
                createNode();//创建zk节点
            }
            channelFuture.channel().closeFuture().sync();

            //channelFuture2.channel().closeFuture().sync();     //可以绑定多个
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    /**
     * 创建zk节点
     * @throws Exception
     */
    public void createNode()throws  Exception{
        //建立节点 指定节点类型（不加withMode默认为持久类型节点）、路径、数据内容
        ExecutorService pool = Executors.newCachedThreadPool();
        String localIp = IPUtil.getMyIPLocal();
        String tempEnv = "";
    }
}

