package system.gagaduck.systemservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class KeyConfig {

    @Bean
    public String generateKey() {
        return UUID.randomUUID().toString();
    }
}

