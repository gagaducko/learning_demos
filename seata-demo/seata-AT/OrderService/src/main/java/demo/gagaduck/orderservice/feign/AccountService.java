package demo.gagaduck.orderservice.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

// 本来最好是用openfeign得，便于操作直接用http
@Service
public class AccountService {

    private final RestTemplate restTemplate;

    @Autowired
    public AccountService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void debit(Long userId, BigDecimal totalPrice) {
        String url = "http://localhost:8081/debit?userId=" + userId + "&amount=" + totalPrice; // 远程服务的接口URL
        try {
            restTemplate.postForObject(url,null, String.class); // 发起Post请求
        } catch (Exception e) {
            // 处理调用失败的情况
            throw e;
        }
    }
}
