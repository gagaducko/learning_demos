package demo.gagaduck.kafkademo.consumer;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
    @KafkaListener(topics = {"PushTopic","test"},groupId = "123")
    public void consume(String message){
        System.out.println("接收到消息："+message);
    }
}
