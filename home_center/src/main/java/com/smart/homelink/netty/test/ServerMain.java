package com.smart.homelink.netty.test;

import com.smart.homelink.netty.server.*;

/**
 * Author : syl
 * datetime ：2018/7/25.
 */
public class ServerMain {

    public static void main(String[] args) {
        NettyServerBootstrap nettyServerBootstrap = new NettyServerBootstrap(
                9000,8899 ,"阿萨大大 阿斯顿撒地方阿斯蒂芬阿斯蒂芬爱的色放" );
        Thread th1 = new Thread(nettyServerBootstrap);
        th1.start();


    }
}
