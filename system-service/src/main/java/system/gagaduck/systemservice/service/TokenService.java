package system.gagaduck.systemservice.service;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import system.gagaduck.systemservice.utils.JwtUtil;

import java.util.concurrent.TimeUnit;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TokenService {
    private static final String TOKEN_PREFIX = "token:";

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public String createToken(String name, String content) {
        String token = JwtUtil.generateToken(name, content);
        redisTemplate.opsForValue().set(TOKEN_PREFIX + name, token, 1, TimeUnit.HOURS);
        return token;
    }

    public void updateTokenName(String oldName, String newName) {
        String token = redisTemplate.opsForValue().get(TOKEN_PREFIX + oldName);
        if (token != null) {
            redisTemplate.delete(TOKEN_PREFIX + oldName);
            redisTemplate.opsForValue().set(TOKEN_PREFIX + newName, token, 1, TimeUnit.HOURS);
        }
    }

    public void deleteToken(String name) {
        redisTemplate.delete(TOKEN_PREFIX + name);
    }

    public Map<String, String> getAllTokens() {
        return redisTemplate.keys(TOKEN_PREFIX + "*").stream()
                .collect(Collectors.toMap(
                        key -> key.substring(TOKEN_PREFIX.length()),
                        key -> redisTemplate.opsForValue().get(key)
                ));
    }

    public String verifyToken(String token) {
        try {
            Claims claims = JwtUtil.parseToken(token);
            System.out.println(claims);
            String content = claims.get("content", String.class);
            return content;
        } catch (Exception e) {
            // Handle the exception according to your needs, e.g., log it, return null, etc.
            return null;
        }
    }
}
