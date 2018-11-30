package com.kelent.timertask.service;

import com.kelent.netty.NettyUtil;

/**
 * Created by think on 2018/3/29.
 */
public class NettyTest implements Runnable  {

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(5000);
                NettyUtil nettyUtil = new NettyUtil();
               // nettyUtil.match("你好 我是服务端 给你发送消息了");
            }catch (Exception e){

            }

        }
    }


}
