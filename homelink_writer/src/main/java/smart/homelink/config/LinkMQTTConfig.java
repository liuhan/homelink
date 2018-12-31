package smart.homelink.config;


import com.alibaba.fastjson.JSON;
import com.smart.homelink.model.AirSensorRecord;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import smart.homelink.service.AirSensorRecordService;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

@Configuration
@IntegrationComponentScan
@ConditionalOnBean(LinkMQTT.class)
public class LinkMQTTConfig {
    private static final Logger log = LoggerFactory.getLogger(LinkMQTTConfig.class);
    @Resource
    LinkMQTT linkMQTT;
    @Autowired
    private AirSensorRecordService airSensorRecordService;
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
    public MessageChannel linkMQTTChannel() {
        return new DirectChannel();
    }


    @Bean
    public MessageProducer inbound() {
        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(linkMQTT.getClientId(), linkMQTTClientFactory(), linkMQTT.getDefaultTopic());
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(1);
        adapter.setOutputChannel(linkMQTTChannel());
        return adapter;
    }


    //通过通道获取数据
    @Bean
    @ServiceActivator(inputChannel = "linkMQTTChannel")
    public MessageHandler linkMQTTHandler() {
        return new MessageHandler() {
            @Override
            public void handleMessage(Message<?> message) throws MessagingException {
                String json = message.getPayload().toString();
                Map map = JSON.parseObject(json ,Map.class);
                //根据组件类型写入不同的表
                AirSensorRecord airSensor = new AirSensorRecord();
                airSensor.setTemp((BigDecimal)map.get("T"));
                airSensor.setAltitude((BigDecimal)map.get("altitude"));
                airSensor.setHumidity((BigDecimal)map.get("humidity"));
                airSensor.setQnh((BigDecimal)map.get("QNH"));
                airSensor.setHomeId(((Integer)map.get("homeId")).longValue());
                airSensor.setcId(((Integer)map.get("componentId")).longValue());
                airSensor.setCreateTime(new Date());
                log.info("收到记录:{}",JSON.toJSONString(airSensor));
                airSensorRecordService.save(airSensor);
            }

        };
    }

}
