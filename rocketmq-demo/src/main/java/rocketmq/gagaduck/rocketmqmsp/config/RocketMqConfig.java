package rocketmq.gagaduck.rocketmqmsp.config;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 当使用RocketMQTemplate时，需要配置RocketMQTemplate bean，在spring boot 3+,不配置会出现问题
@Configuration
public class RocketMqConfig {

    @Value("${rocketmq.producer.group}")
    private String producerGroup;

    @Value("${rocketmq.name-server}")
    private String nameServer;

    @Bean("RocketMqTemplate")
    public RocketMQTemplate rocketMqTemplate(){
        RocketMQTemplate rocketMqTemplate = new RocketMQTemplate();
        DefaultMQProducer defaultMqProducer = new DefaultMQProducer();
        defaultMqProducer.setProducerGroup(producerGroup);
        defaultMqProducer.setNamesrvAddr(nameServer);
        rocketMqTemplate.setProducer(defaultMqProducer);
        return rocketMqTemplate;
    }
}