package demo.gagaduck.orderservice.config;

import com.alibaba.cloud.seata.rest.SeataRestTemplateInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
        interceptors.add(new SeataRestTemplateInterceptor());
        restTemplate.setInterceptors(interceptors);
        return restTemplate;
    }
}
