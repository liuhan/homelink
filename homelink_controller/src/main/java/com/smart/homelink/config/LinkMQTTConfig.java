package com.smart.homelink.config;


import com.alibaba.fastjson.JSON;
import com.smart.util.redis.RedisProperties;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

import javax.annotation.Resource;
import java.util.Map;

@Configuration
@IntegrationComponentScan
@ConditionalOnBean(LinkMQTT.class)
public class LinkMQTTConfig {
    private static final Logger log = LoggerFactory.getLogger(LinkMQTTConfig.class);
    @Resource
    LinkMQTT linkMQTT;

    @Bean
    public MqttConnectOptions linkMQTTConnectOptions(){
        MqttConnectOptions mqttConnectOptions=new MqttConnectOptions();
//        mqttConnectOptions.setUserName(username);
//        mqttConnectOptions.setPassword(password.toCharArray());
        mqttConnectOptions.setServerURIs(new String[]{linkMQTT.getUrl()});
        mqttConnectOptions.setKeepAliveInterval(2);
        return mqttConnectOptions;
    }

    @Bean
    public MqttPahoClientFactory linkMQTTClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        factory.setConnectionOptions(linkMQTTConnectOptions());
        return factory;
    }
    @Bean
    public MessageChannel linkMQTTOutputChannel() {
        return new DirectChannel();
    }
    @Bean
    public MessageProducer inbound() {
        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(linkMQTT.getSubscribeClientId(), linkMQTTClientFactory(),linkMQTT.getSubscribeTopic());
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(1);
        adapter.setOutputChannel(linkMQTTOutputChannel());
        return adapter;
    }
    @Bean
    @ServiceActivator(inputChannel = "linkMQTTOutputChannel")
    public MessageHandler handler() {
        return new MessageHandler() {
            @Override
            public void handleMessage(Message<?> message) throws MessagingException {
                System.out.println(message.getPayload());
            }

        };
    }

    @Bean
    @ServiceActivator(inputChannel = "linkMQTTInputChannel")
    public MessageHandler linkMQTTInputHandler() {
        MqttPahoMessageHandler messageHandler =  new MqttPahoMessageHandler(linkMQTT.getPushClientId(), linkMQTTClientFactory()  );
        messageHandler.setAsync(true);
        messageHandler.setDefaultTopic(linkMQTT.getSubscribeTopic());
        return messageHandler;
    }

}
