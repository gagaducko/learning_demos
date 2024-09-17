package demo.gagaduck.kafkademo.producer;

import jakarta.annotation.Resource;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    @Resource
    private KafkaTemplate<String,String> kafkaTemplate;

    public String sendKafkaTest(String topic, String msg) {
        kafkaTemplate.send(topic, msg);
        return "send topic: " + topic + "and msg is: " + msg;
    }
}
