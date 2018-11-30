package com.kelent.netty.server;


import com.smart.util.netty.NettyIMMessageType;
import com.smart.util.netty.model.NettyMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

/**
 * 检测心跳
 * Author : syl
 * datetime ：2018/4/10.
 */
public class NettyHeartServerHandler extends ChannelInboundHandlerAdapter {
    Logger logger = LoggerFactory.getLogger(NettyServerHandler.class);
    /**
     * 读取数据        //回写响应消息
     * @param ctx
     * @param msg
     * @throws UnsupportedEncodingException
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws UnsupportedEncodingException {
        //读取数据
        if (msg instanceof NettyMessage) {
            NettyMessage nettyMessage = (NettyMessage) msg;
            logger.info(nettyMessage.getCode() + "-->" +   nettyMessage.getTerminalNo() +  "===>" +  ctx.channel().toString());
            if(NettyIMMessageType.CLOUD_ABINET_CLENT_SERVER_HEART == nettyMessage.getCode()){
                ctx.channel().writeAndFlush(nettyMessage);
            }else {
               ctx.fireChannelRead(msg);//返回另外一个hander处理
            }
        } else {
            ctx.fireChannelRead(msg);//返回另外一个hander处理
        }
    }
}