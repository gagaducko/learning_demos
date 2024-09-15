package system.gagaduck.systemservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import system.gagaduck.systemservice.service.TokenService;

import java.util.Map;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/tokens")
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @PostMapping("/create")
    public String createToken(@RequestParam String name, @RequestParam String content) {

        return tokenService.createToken(name, content);
    }

    @PutMapping("/update")
    public void updateTokenName(@RequestParam String oldName, @RequestParam String newName) {
        log.info("token has update from" + oldName + "to" + newName);
        tokenService.updateTokenName(oldName, newName);
    }

    @DeleteMapping("/delete")
    public void deleteToken(@RequestParam String name) {
        log.info("token " + name + " has deleted");
        tokenService.deleteToken(name);
    }

    @GetMapping("/get")
    public Map<String, String> getAllTokens() {
        return tokenService.getAllTokens();
    }

    @GetMapping("/verify/{token}")
    public String verifyToken(@PathVariable String token) {
        String ret = tokenService.verifyToken(token);
        log.info("token " + token + " is verified and return is " + ret);
        System.out.println(ret);
        return ret;
    }
}
