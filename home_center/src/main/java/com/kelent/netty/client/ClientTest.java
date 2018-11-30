package com.kelent.netty.client;

import io.netty.bootstrap.Bootstrap;

/**
 * Author : syl
 * datetime ：2018/4/8.
 */
public class ClientTest {

    public static void main(String[] args) {

        Bootstrap bootStrap = new Bootstrap();
        NettyClient nettyClient = new NettyClient("192.168.8.100" , 9000 ,bootStrap);
      new Thread(nettyClient).start();
/*
       NettyClient nettyClient2 = new NettyClient("192.168.1.200" , 9000 ,bootStrap);
       new Thread(nettyClient2).start();

        NettyClient nettyClient3 = new NettyClient("192.168.1.200" , 9000 ,bootStrap);
        new Thread(nettyClient3).start();

        NettyClient nettyClient4 = new NettyClient("192.168.1.200" , 9000 ,bootStrap);
        new Thread(nettyClient4).start();

        NettyClient nettyClient5 = new NettyClient("192.168.1.200" , 9000 ,bootStrap);
        new Thread(nettyClient5).start();

        NettyClient nettyClient6 = new NettyClient("192.168.1.200" , 9000 ,bootStrap);
        new Thread(nettyClient6).start();
        NettyClient nettyClient7 = new NettyClient("192.168.1.200" , 9000 ,bootStrap);
        new Thread(nettyClient7).start();

        NettyClient nettyClient8 = new NettyClient("192.168.1.200" , 9000 ,bootStrap);
        new Thread(nettyClient8).start();

        NettyClient nettyClient9 = new NettyClient("192.168.1.200" , 9000 ,bootStrap);
        new Thread(nettyClient9).start();

        NettyClient nettyClient10 = new NettyClient("192.168.1.200" , 9000 ,bootStrap);
        new Thread(nettyClient10).start();*/
        System.out.println("--->");
    }
}
