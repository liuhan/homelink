package com.smart.homelink.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.controller.mqtt")
public class ControllerMQTT {

    private String username;

    private String password;

    private String url;

    private String pushClientId;

    private String subscribeClientId;

    public String getUrl() {
        return url;
    }

    public String getPushClientId() {
        return pushClientId;
    }

    public void setPushClientId(String pushClientId) {
        this.pushClientId = pushClientId;
    }

    public String getSubscribeClientId() {
        return subscribeClientId;
    }

    public void setSubscribeClientId(String subscribeClientId) {
        this.subscribeClientId = subscribeClientId;
    }

    public String getSubscribeTopic() {
        return subscribeTopic;
    }

    public void setSubscribeTopic(String subscribeTopic) {
        this.subscribeTopic = subscribeTopic;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String subscribeTopic;

    private int completionTimeout ;   //连接超时

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public int getCompletionTimeout() {
        return completionTimeout;
    }

    public void setCompletionTimeout(int completionTimeout) {
        this.completionTimeout = completionTimeout;
    }

}
