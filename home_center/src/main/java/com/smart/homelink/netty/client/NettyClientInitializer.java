package com.smart.homelink.netty.client;


import com.smart.homelink.netty.util.Serial.kryo.KryoCodecUtil;
import com.smart.homelink.netty.util.Serial.kryo.KryoDecoder;
import com.smart.homelink.netty.util.Serial.kryo.KryoEncoder;
import com.smart.homelink.netty.util.Serial.kryo.KryoPoolFactory;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * Created by think on 2018/3/28.
 */
public class NettyClientInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    public void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        // 字符串解码 和 编码，需要做TCP拆包粘包
/*      pipeline.addLast("decoder", new StringDecoder());
        pipeline.addLast("encoder", new StringEncoder());*/
        //设置发送消息编码器
  /*     pipeline.addLast(new ObjectEncoder());
       pipeline.addLast(new ObjectDecoder(10*1024, ClassResolvers.cacheDisabled(this.getClass().getClassLoader())));

*/
        //jboss  Marshalling
/*      pipeline.addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
        pipeline.addLast(MarshallingCodeCFactory.buildMarshallingEncoder());*/
        //kryo
//        pipeline.addLast("encoder" , new KryoEncoder());
//        pipeline.addLast("decoder" , new KryoDecoder());

        KryoCodecUtil util = new KryoCodecUtil(KryoPoolFactory.getKryoPoolInstance());
        pipeline.addLast("encoder" , new KryoEncoder(util));
        pipeline.addLast("decoder" , new KryoDecoder(util));
        pipeline.addLast("handler", new NettyClientHandler());
    }
}