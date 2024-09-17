package demo.gagaduck.productservice.controller;

import demo.gagaduck.productservice.service.ProductService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Resource
    private ProductService productService;

    @PostMapping("/reduce")
    public void reduceStock(@RequestParam("productId") Long productId,
                            @RequestParam("amount") int amount) {
        productService.reduceStock(productId, amount);
    }
}
