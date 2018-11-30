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
package com.kelent.netty.test;

import com.alibaba.fastjson.JSONObject;
import com.kelent.util.netty.Serial.MessageCodecUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author syl
 * @since 2018/5/17
 */
public class MessageEncoder extends MessageToByteEncoder<Object> {

    @Override
    protected void encode(final ChannelHandlerContext ctx, final Object msg, final ByteBuf out) throws Exception {
        try {
            byte[] body = new String(msg.toString() ).getBytes("utf-8");  //将对象转换为byte，伪代码，具体用什么进行序列化，你们自行选择。此处用的是fastJson
            int dataLength = body.length;  //读取消息的长度
            byte[] body2 = JSONObject.toJSONBytes("0760");
            out.writeInt(0x0760);
            out.writeInt(0x0660);
            out.writeByte(0xFF);
            out.writeInt(dataLength);  //先将消息长度写入，也就是消息头
            out.writeBytes(body);  //消息体中包含我们要发送的数据
        } catch (Exception e) {
            System.out.println(e + "");
        }
    }
}

