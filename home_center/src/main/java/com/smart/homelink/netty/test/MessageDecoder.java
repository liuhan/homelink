/**
 * Copyright (C) 2016 Newland Group Holding Limited
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.smart.homelink.netty.test;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.Arrays;
import java.util.List;

/**
 * @author syl
 * @since 2018/5/17
 */
public class MessageDecoder extends ByteToMessageDecoder {

    final public static int MESSAGE_LENGTH = 13;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {


        if (in.readableBytes() < MESSAGE_LENGTH) { //这个HEAD_LENGTH是我们用于表示头长度的字节数。  由于上面我们传的是一个int类型的值，所以这里HEAD_LENGTH的值为4.
            return;
        }
        in.markReaderIndex();


        byte[] datalen = new byte[13];//读取前13个字节数据存放到datalen中
        in.readBytes(datalen);
        byte[] origina1 = Arrays.copyOfRange(datalen, 0, 4);
        byte[] origina2 = Arrays.copyOfRange(datalen, 4, 8);
        byte[] origina3 = Arrays.copyOfRange(datalen, 8, 9);
        byte[] origina4 = Arrays.copyOfRange(datalen, 9, 13);
        int length1 = byteArrayToInt(origina1);//将字节数组转换为int型
        int length2 = byteArrayToInt(origina2);//将字节数组转换为int型
        int length3 = byteArrayToInt2(origina3);//将字节数组转换为int型
        int length4 = byteArrayToInt(origina4);//将字节数组转换为int型
        byte[] messageBody = new byte[length4];
        in.readBytes(messageBody);
        String recvMsg = new String(messageBody);//将获得数据转为字符串类型
     //   System.out.println(recvMsg);
        out.add(recvMsg);
/*
        Object obj = JSONObject.parse(messageBody);//将byte数据转化为我们需要的对象。伪代码，用什么序列化，自行选择。 此处用的是fastJson
        out.add(obj);*/
    }

    public static int byteArrayToInt2(byte[] b){
        return  b[0]&0xFF;
    }
    public static int byteArrayToInt(byte[] b){
        return b[3]&0xFF | (b[2]&0xFF) << 8 | (b[1]&0xFF) << 16 | (b[0]&0xFF) << 24;
    }
}

