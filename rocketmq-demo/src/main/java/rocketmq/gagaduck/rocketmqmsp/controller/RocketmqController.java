package rocketmq.gagaduck.rocketmqmsp.controller;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rocketmq.gagaduck.rocketmqmsp.producer.RocketmqProducer;

import java.io.UnsupportedEncodingException;

@RestController
public class RocketmqController {
    @Autowired
    private RocketmqProducer rocketmqProducer;

    @RequestMapping("/push")
    public String pushMsg(@RequestParam("topic") String topic,
                          @RequestParam("tag") String tag,
                          @RequestParam("message") String message) {
        try {
            return rocketmqProducer.send(topic, tag, message);
        } catch (InterruptedException | RemotingException | MQClientException | MQBrokerException | UnsupportedEncodingException e) {
            e.printStackTrace();
            return "ERROR";
        }
    }
}
