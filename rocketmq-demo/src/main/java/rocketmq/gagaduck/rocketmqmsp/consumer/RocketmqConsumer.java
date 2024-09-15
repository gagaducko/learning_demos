package rocketmq.gagaduck.rocketmqmsp.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RocketmqConsumer implements CommandLineRunner {

    @Value("${rocketmq.name-server}")
    private String nameServer;

    @Value("${rocketmq.producer.group}")
    private String defaultProducerGroup;

    // 初始化监听信息
    public void messageListener(){
        // default consumer group
        DefaultMQPushConsumer consumer=new DefaultMQPushConsumer(defaultProducerGroup);
        // set name server
        consumer.setNamesrvAddr(nameServer);
        try {
            // 订阅PushTopic下Tag为push的消息,都订阅消息
            consumer.subscribe("PushTopic", "push");
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
            consumer.setConsumeMessageBatchMaxSize(1);
            // 监听
            consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
                for(Message msg:msgs){
                    System.out.println("消费者接收到了消息："+ new String(msg.getBody()));
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            });
            consumer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run(String... args) throws Exception {
        this.messageListener();
    }
}