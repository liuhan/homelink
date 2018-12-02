package com.smart.homelink.timertask.service;

import com.smart.homelink.netty.server.NettyHttpServerBootstrap;
import com.smart.homelink.netty.server.NettyServerBootstrap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by think on 2018/3/29.
 */

@Component
public class NettyServerService {
    //netty 服务端端口
    @Value("${netty.server.port}")
    private int nettyPort ;

    //netty 服务外网IP地址
    @Value("${netty.server.networkIp:192.168.11.7}")
    private String networkIp;

    @Value("${netty.server.http.port:8899}")
    private int nettyHttpPort ;
    //netty的环境
    private static String profile = "";

    @Value("${spring.profiles.active:dev}")
    public  void setProfile(String profile) {
        NettyServerService.profile = profile;
    }

    public static String getProfile() {
        return profile;
    }

    @Bean
    public NettyServerService getNettyServer() throws Exception {
        NettyServerBootstrap nettyServerBootstrap = new NettyServerBootstrap(nettyPort,nettyHttpPort ,networkIp );
        NettyHttpServerBootstrap nettyHttpServerBootstrap = new NettyHttpServerBootstrap(nettyHttpPort );
        Thread th1 = new Thread(nettyServerBootstrap);
        Thread th2 = new Thread(nettyHttpServerBootstrap);
        th1.start();
        th2.start();

        return this;
    }

    public int getNettyPort() {
        return nettyPort;
    }

    public void setNettyPort(int nettyPort) {
        this.nettyPort = nettyPort;
    }

    public String getNetworkIp() {
        return networkIp;
    }

    public void setNetworkIp(String networkIp) {
        this.networkIp = networkIp;
    }

    public int getNettyHttpPort() {
        return nettyHttpPort;
    }

    public void setNettyHttpPort(int nettyHttpPort) {
        this.nettyHttpPort = nettyHttpPort;
    }
}
