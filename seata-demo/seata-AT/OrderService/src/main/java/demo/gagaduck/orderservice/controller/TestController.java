package demo.gagaduck.orderservice.controller;

import demo.gagaduck.orderservice.service.OrderService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class TestController {

    @Resource
    private OrderService orderService;

    @PostMapping("/create")
    public void createOrder(@RequestParam("userId") Long userId,
                            @RequestParam("productId") Long productId,
                            @RequestParam("count") int count,
                            @RequestParam("price") BigDecimal price) {
        orderService.createOrder(userId, productId, count, price);
    }
}
