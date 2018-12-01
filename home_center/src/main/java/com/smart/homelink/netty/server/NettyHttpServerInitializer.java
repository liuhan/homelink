package com.smart.homelink.netty.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * 初始化
 * Created by think on 2018/3/28.
 */
public class NettyHttpServerInitializer extends
        ChannelInitializer<SocketChannel> {

    private int serverPort;
    public NettyHttpServerInitializer( int serverPort){
        this.serverPort = serverPort;
    }


    @Override
    public void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("http-decoder" , new HttpRequestDecoder());
        pipeline.addLast("http-aggregator" , new HttpObjectAggregator(65536));
        pipeline.addLast("http-encoder" , new HttpResponseEncoder());
        pipeline.addLast("http-chunked" , new ChunkedWriteHandler());

        pipeline.addLast(new NettyHttpServerHandler());

    }
}
