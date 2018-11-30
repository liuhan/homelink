package com.kelent.netty.test;

import com.kelent.util.netty.model.NettyMessage;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by think on 2018/3/28.
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter {
    Logger logger = LoggerFactory.getLogger(NettyClientHandler.class);
    private ChannelHandlerContext ctx;

    /**
     * 在线
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        this.ctx = ctx;
    }

    /*
      未在线
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

        super.channelInactive(ctx);
    }

    public boolean sendMsg(String msg) {
       ChannelFuture channelFuture =  ctx.channel().writeAndFlush(msg);
        return channelFuture.isSuccess();
    }

    /**
     * 收到服务器消息后调用
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws UnsupportedEncodingException {
        System.out.println("msg--->"  +msg);

    }

    /**
     * 发生异常时调用
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
        System.out.println("异常");


    }
}
