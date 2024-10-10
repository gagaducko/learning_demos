package demo.gagaduck.orderservice.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

// 本来最好是用openfeign得，便于操作直接用http
@Service
public class ProductService {

    private final RestTemplate restTemplate;

    @Autowired
    public ProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void reduceStock(Long productId, int count) {
        String url = "http://localhost:8080/reduce?productId=" + productId + "&amount=" + count; // 远程服务的接口URL
        try {
            restTemplate.postForObject(url,null, String.class); // 发起Post请求
        } catch (Exception e) {
            // 处理调用失败的情况
            throw e;
        }
    }
}
