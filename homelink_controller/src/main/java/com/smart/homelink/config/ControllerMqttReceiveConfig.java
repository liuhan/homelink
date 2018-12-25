package com.smart.homelink.config;


import com.alibaba.fastjson.JSON;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

import java.util.Map;

@Configuration
@IntegrationComponentScan
public class ControllerMqttReceiveConfig {

    @Value("${spring.cmqtt.username}")
    private String username;

    @Value("${spring.cmqtt.password}")
    private String password;

    @Value("${spring.cmqtt.url}")
    private String hostUrl;

    @Value("${spring.cmqtt.client.id}")
    private String clientId;

    @Value("${spring.cmqtt.default.topic}")
    private String defaultTopic;

    @Value("${spring.cmqtt.completionTimeout}")
    private int completionTimeout ;   //连接超时

    @Bean
    public MqttConnectOptions getCMqttConnectOptions(){
        MqttConnectOptions mqttConnectOptions=new MqttConnectOptions();
//        mqttConnectOptions.setUserName(username);
//        mqttConnectOptions.setPassword(password.toCharArray());
        mqttConnectOptions.setServerURIs(new String[]{hostUrl});
        mqttConnectOptions.setKeepAliveInterval(2);
        return mqttConnectOptions;
    }
    @Bean
    public MqttPahoClientFactory cmqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        factory.setConnectionOptions(getCMqttConnectOptions());
        return factory;
    }

    //接收通道
    @Bean
    public MessageChannel cmqttInputChannel() {
        return new DirectChannel();
    }

    //配置client,监听的topic
    @Bean
    public MessageProducer cinbound() {
        MqttPahoMessageDrivenChannelAdapter adapter =
                new MqttPahoMessageDrivenChannelAdapter(clientId, cmqttClientFactory(),
                        defaultTopic);
        adapter.setCompletionTimeout(completionTimeout);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(1);
        adapter.setOutputChannel(cmqttInputChannel());
        return adapter;
    }

    //通过通道获取数据
    @Bean
    @ServiceActivator(inputChannel = "cmqttInputChannel")
    public MessageHandler chandler() {
        return new MessageHandler() {
            @Override
            public void handleMessage(Message<?> message) throws MessagingException {
                String json = message.getPayload().toString();
                Map map = JSON.parseObject(json ,Map.class);
                //判断网络连通情况
                //转发到线上
            }
        };
    }

}
