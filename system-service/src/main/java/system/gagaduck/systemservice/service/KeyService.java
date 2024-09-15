package system.gagaduck.systemservice.service;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import system.gagaduck.systemservice.repository.KeyRepository;

import java.util.Set;

@Service
public class KeyService {

    @Resource
    private KeyRepository keyRepository;

    public String generateKey() {
        String key = keyRepository.generateKey();
        keyRepository.save(key);
        return key;
    }

    public boolean validateKey(String key) {
        return keyRepository.exists(key);
    }

    public String modifyKey(String key) {
        String newKey = keyRepository.generateKey();
        keyRepository.update(key, newKey);
        return newKey;
    }

    public Set<String> getAllKeys() {
        return keyRepository.findAll();
    }

    public void deleteKey(String key) {
        keyRepository.delete(key);
    }
}
