package com.smart.homelink.netty.model;

import java.io.Serializable;

/**
 * Author : syl
 * datetime ：2018/4/8.
 */
public class NettyWebsocketMessage implements Serializable {
    private int code;//类型 NettyIMWebsocketMessageType
    private String terminalNo;//设备编号
    private Long userId;//用户Id
    private Object data;//传输数据//主要用户加密解密

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getTerminalNo() {
        return terminalNo;
    }

    public void setTerminalNo(String terminalNo) {
        this.terminalNo = terminalNo;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
