package com.kelent.netty.test;

import io.netty.bootstrap.Bootstrap;

/**
 * Author : syl
 * datetime ：2018/7/25.
 */
public class ClientMain {

    public static void main(String[] args) {
        Bootstrap bootStrap = new Bootstrap();
        NettyClient nettyClient = new NettyClient("localhost" , 9000 ,bootStrap);
        new Thread(nettyClient).start();
        System.out.println("--->");
    }
}
