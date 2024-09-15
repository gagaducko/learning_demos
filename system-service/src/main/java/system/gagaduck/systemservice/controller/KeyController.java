package system.gagaduck.systemservice.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import system.gagaduck.systemservice.service.KeyService;

import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping("/keys")
public class KeyController {

    @Resource
    private KeyService keyService;

    @PostMapping("/generate")
    public String generateKey() {
        return keyService.generateKey();
    }

    @GetMapping("/validate/{key}")
    public boolean validateKey(@PathVariable String key) {
        return keyService.validateKey(key);
    }

    @PutMapping("/modify/{key}")
    public String modifyKey(@PathVariable String key) {
        return keyService.modifyKey(key);
    }

    @GetMapping("/get")
    public Set<String> getAllKeys() {
        return keyService.getAllKeys();
    }

    @DeleteMapping("/delete/{key}")
    public void deleteKey(@PathVariable String key) {
        keyService.deleteKey(key);
    }
}
