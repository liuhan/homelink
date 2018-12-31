package com.smart.homelink.config;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;

@MessagingGateway(defaultRequestChannel = "controllerMQTTChannel")
public interface ControllerMQTTServer {
    void sendToMqtt(String data, @Header(MqttHeaders.TOPIC) String topic);
}
