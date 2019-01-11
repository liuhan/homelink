package com.smart.homelink.config;


import com.alibaba.fastjson.JSON;
import com.smart.homelink.service.PlayerService;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
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
import java.math.BigDecimal;
import java.util.Map;

@Configuration
@IntegrationComponentScan
@ConditionalOnBean({ControllerMQTT.class,LinkMQTTConfig.class})
public class ControllerMQTTConfig {
    private static final Logger log = LoggerFactory.getLogger(ControllerMQTTConfig.class);
    @Resource
    ControllerMQTT controllerMQTT;
    @Autowired
    private LinkMQTTServer linkMQTTServer;
    private int count = 0;

    public static BigDecimal temp ;

    @Autowired
    public PlayerService playerService;
    @Resource
    LinkMQTT linkMQTT;
    @Bean
    public MqttConnectOptions ControllerMQTTConnectOptions(){
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
//        mqttConnectOptions.setUserName(controllerMQTT.getUsername());
//        mqttConnectOptions.setPassword(controllerMQTT.getPassword().toCharArray());
        mqttConnectOptions.setServerURIs(new String[]{controllerMQTT.getUrl()});
        mqttConnectOptions.setKeepAliveInterval(2);
        return mqttConnectOptions;
    }
    @Bean
    public MqttPahoClientFactory controllerMQTTClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        factory.setConnectionOptions(ControllerMQTTConnectOptions());
        return factory;
    }
    @Bean
    public MessageChannel controllerMQTTOutputChannel() {
        return new DirectChannel();
    }
    @Bean
    public MessageProducer cinbound() {
        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(controllerMQTT.getSubscribeClientId(), controllerMQTTClientFactory(),controllerMQTT.getSubscribeTopic());
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(1);
        adapter.setOutputChannel(controllerMQTTOutputChannel());
        return adapter;
    }
    @Bean
    @ServiceActivator(inputChannel = "controllerMQTTOutputChannel")
    public MessageHandler controllerMQTTOutputHandler() {
        return new MessageHandler() {
            @Override
            public void handleMessage(Message<?> message) throws MessagingException {
                String json = message.getPayload().toString();
                Map map = JSON.parseObject(json ,Map.class);
                map.put("homeId",1);
                log.info("收到消息:{}",JSON.toJSONString(map));

                temp =  (BigDecimal)map.get("T");
                if (temp.compareTo(new BigDecimal("24")) < 0 && count == 0) {
                    playerService.play("你家目前温度为："+temp+"摄氏度，低于警界温度，请注意防寒保暖！");
                    count++;
                }

                linkMQTTServer.sendToMqtt(JSON.toJSONString(map) , linkMQTT.getPushTopic());
            }

        };
    }

    @Bean
    @ServiceActivator(inputChannel = "controllerMQTTInputChannel")
    public MessageHandler controllerMQTTInputHandler() {
        MqttPahoMessageHandler messageHandler =  new MqttPahoMessageHandler(controllerMQTT.getPushClientId(), controllerMQTTClientFactory()  );
        messageHandler.setAsync(true);
        messageHandler.setDefaultTopic(controllerMQTT.getSubscribeTopic());
        return messageHandler;
    }
}
