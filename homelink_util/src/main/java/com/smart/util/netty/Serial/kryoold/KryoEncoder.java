package com.smart.util.netty.Serial.kryoold;

/**
 * Author : syl
 * datetime ：2018/4/9.
 */

import com.kelent.util.netty.model.NettyMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class KryoEncoder extends MessageToByteEncoder<NettyMessage> {

    @Override
    protected void encode(ChannelHandlerContext ctx, NettyMessage message, ByteBuf out) throws Exception {
         KryoSerializer.serialize(message, out);

       // KryoSerializer2.serialize(message,out);
        ctx.flush();
    }
}
