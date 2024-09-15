package system.gagaduck.systemservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/log")
public class LogController {


    @GetMapping("/test")
    public String showLog(){
        log.warn("日志测试,gagaduck");
        return "ok";
    }

    @GetMapping("/messageInfo/{message}")
    public String sendMessageInfo(@PathVariable String message) {
        log.info(message);
        return "info message!";
    }


    @GetMapping("/messageWarn/{message}")
    public String sendMessageWarn(@PathVariable String message) {
        log.warn(message);
        return "warn message!";
    }

    @GetMapping("/messageError/{message}")
    public String sendMessageError(@PathVariable String message) {
        log.error(message);
        return "error message!";
    }

}