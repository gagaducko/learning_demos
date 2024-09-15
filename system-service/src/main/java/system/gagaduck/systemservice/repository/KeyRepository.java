package system.gagaduck.systemservice.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

@Repository
public class KeyRepository {

    private static final String KEY_SET = "keys";

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public String generateKey() {
        return UUID.randomUUID().toString();
    }

    public void save(String key) {
        redisTemplate.opsForSet().add(KEY_SET, key);
    }

    public boolean exists(String key) {
        return Boolean.TRUE.equals(redisTemplate.opsForSet().isMember(KEY_SET, key));
    }

    public void update(String oldKey, String newKey) {
        delete(oldKey);
        save(newKey);
    }

    public Set<String> findAll() {
        return redisTemplate.opsForSet().members(KEY_SET);
    }

    public void delete(String key) {
        redisTemplate.opsForSet().remove(KEY_SET, key);
    }
}
