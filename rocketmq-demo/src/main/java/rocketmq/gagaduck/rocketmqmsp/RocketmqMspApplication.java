package rocketmq.gagaduck.rocketmqmsp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RocketmqMspApplication {
    public static void main(String[] args) {
        System.setProperty("rocketmq.client.logUseSlf4j", "true");
        System.setProperty("rocketmq.client.logLevel", "ERROR");
        SpringApplication.run(RocketmqMspApplication.class, args);
    }

}
