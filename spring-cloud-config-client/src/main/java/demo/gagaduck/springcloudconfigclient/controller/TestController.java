package demo.gagaduck.springcloudconfigclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class TestController {

    @Value("${env}")
    private String env;

    @Value("${user.username}")
    private String username;

    @Value("${user.password}")
    private String password;

    @RequestMapping("/env")
    public String env() {
        return "env in test is: " + env;
    }

    @RequestMapping("/username")
    public String username() {
        return "username in test is: " + username;
    }

    @RequestMapping("/password")
    public String password() {
        return "password in test is: " + password;
    }

}