package com.smart.util.netty.Serial.kryoold;

/**
 * Author : syl
 * datetime ：2018/4/9.
 */
import java.util.List;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class KryoDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
      Object obj = KryoSerializer.deserialize(in ,ctx);
        //Object obj = KryoSerializer2.deserialize(in);
        if(obj != null){
            out.add(obj);

        }
    }

}