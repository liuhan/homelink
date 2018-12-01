package com.smart.homelink.netty.test;

import com.smart.homelink.util.netty.Serial.kryo.KryoCodecUtil;
import com.smart.homelink.util.netty.Serial.kryo.KryoDecoder;
import com.smart.homelink.util.netty.Serial.kryo.KryoEncoder;
import com.smart.homelink.util.netty.Serial.kryo.KryoPoolFactory;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * 初始化
 * Created by think on 2018/3/28.
 */
public class NettyServerInitializer extends
        ChannelInitializer<SocketChannel> {

    private String serverIP ;
    private int serverPort;
    private int serverHttpPort;
    private String uuid;
    public NettyServerInitializer(String serverIP , int serverPort , int serverHttpPort , String uuid){
        this.serverIP = serverIP;
        this.serverPort = serverPort;
        this.serverHttpPort = serverHttpPort;
        this.uuid = uuid;
    }


    @Override
    public void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        // 字符串解码 和 编码 ,注意TCP拆包 粘包
      /*pipeline.addLast("decoder", new StringDecoder());
        pipeline.addLast("encoder", new StringEncoder());*/
        //设置发送消息编码器  java
      /*   pipeline.addLast(new ObjectEncoder());
        pipeline.addLast(new ObjectDecoder(10 * 1024 ,    ClassResolvers.weakCachingConcurrentResolver(this
                .getClass().getClassLoader())));*/
     //jboss  Marshalling
    /*  pipeline.addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
        pipeline.addLast(MarshallingCodeCFactory.buildMarshallingEncoder());*/
        KryoCodecUtil util = new KryoCodecUtil(KryoPoolFactory.getKryoPoolInstance());
        pipeline.addLast("encoder" , new KryoEncoder(util));
        pipeline.addLast("decoder" , new KryoDecoder(util));
      /*  pipeline.addLast("encoder" , new MessageEncoder());
        pipeline.addLast("decoder" , new MessageDecoder());*/
        pipeline.addLast("handler", new NettyServerHandler());

    }
}
