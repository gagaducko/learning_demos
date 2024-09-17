package demo.gagaduck.kafkademo.controller;

import demo.gagaduck.kafkademo.producer.KafkaProducer;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {

    @Resource
    private KafkaProducer kafkaProducer;

    @PostMapping("/test")
    public String pushMsg(@RequestParam("topic") String topic,
                          @RequestParam("message") String message) {
        return kafkaProducer.sendKafkaTest(topic, message);
    }

}
