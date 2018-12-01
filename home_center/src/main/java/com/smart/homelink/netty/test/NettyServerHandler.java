package com.smart.homelink.netty.test;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

/**
 * Netty server handler
 * Created by think on 2018/3/28.
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    Logger logger = LoggerFactory.getLogger(NettyServerHandler.class);
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
       //
    }

    /**
     * 连接
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception { // (5)
        Channel channel = ctx.channel();
        logger.info("connect start ------------》{}", channel.toString());

    }
    /**
     * 读取数据        //回写响应消息
     * @param ctx
     * @param msg
     * @throws UnsupportedEncodingException
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Channel channel = ctx.channel();
        logger.info("服务端读取数据-->{}" , msg);
        ctx.channel().writeAndFlush("11111 ");
    }

    /**
     * 异常处理或者掉线
     * @param ctx
     * @param cause
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error("云柜connect exception-------->{}" , ctx.channel().toString());
        cause.printStackTrace();
        ctx.close();
    }

  /*
    掉线
   */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        logger.error("云柜connect timeout------->{}" , ctx.channel().toString());
        super.channelInactive(ctx);
    }
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }
}
