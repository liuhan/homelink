package com.smart.util.netty.model;

import java.io.Serializable;

/**
 * Author : syl
 * 设备连接IM对象节点对象
 * datetime ：2018/7/23.
 */
public class TerminalNodeModel implements Serializable {

    private String serverIp;//IM服务器外网IP
    private String serverLocalIp;//IM服务器内网IP
    private int serverPort;//socket 端口号
    private int serverHttPort ;//http和websocket 端口号
    private String connectInfo;//连接信息
    private String extData;//扩展对象信息

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public String getServerLocalIp() {
        return serverLocalIp;
    }

    public void setServerLocalIp(String serverLocalIp) {
        this.serverLocalIp = serverLocalIp;
    }

    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public int getServerHttPort() {
        return serverHttPort;
    }

    public void setServerHttPort(int serverHttPort) {
        this.serverHttPort = serverHttPort;
    }

    public String getConnectInfo() {
        return connectInfo;
    }

    public void setConnectInfo(String connectInfo) {
        this.connectInfo = connectInfo;
    }

    public String getExtData() {
        return extData;
    }

    public void setExtData(String extData) {
        this.extData = extData;
    }
}
