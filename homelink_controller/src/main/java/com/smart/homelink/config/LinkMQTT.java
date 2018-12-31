package com.smart.homelink.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.link.mqtt")
public class LinkMQTT {

    private String username;

    private String password;

    private String url;

    private String pushClientId;

    private String subscribeClientId;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String pushTopic;

    public String getSubscribeClientId() {
        return subscribeClientId;
    }

    public void setSubscribeClientId(String subscribeClientId) {
        this.subscribeClientId = subscribeClientId;
    }

    private String subscribeTopic;

    private int completionTimeout ;   //连接超时

    public String getPushClientId() {
        return pushClientId;
    }

    public void setPushClientId(String pushClientId) {
        this.pushClientId = pushClientId;
    }

    public String getPushTopic() {
        return pushTopic;
    }

    public void setPushTopic(String pushTopic) {
        this.pushTopic = pushTopic;
    }

    public String getUsername() {
        return username;
    }


    public String getSubscribeTopic() {
        return subscribeTopic;
    }

    public void setSubscribeTopic(String subscribeTopic) {
        this.subscribeTopic = subscribeTopic;
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
