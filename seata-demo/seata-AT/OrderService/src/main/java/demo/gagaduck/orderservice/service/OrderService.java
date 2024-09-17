package demo.gagaduck.orderservice.service;

import demo.gagaduck.orderservice.feign.AccountService;
import demo.gagaduck.orderservice.feign.ProductService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
public class OrderService {

    @Autowired
    private ProductService productService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GlobalTransactional
    public void createOrder(Long userId, Long productId, int count, BigDecimal price) {
        log.info("Creating order for userId={}, productId={}, count={}, price={}", userId, productId, count, price);

        // 扣减库存
        try {
            productService.reduceStock(productId, count);
        } catch (Exception e) {
            log.error("Error reducing stock", e);
            throw e;
        }

        // 扣减账户余额
        BigDecimal totalPrice = price.multiply(BigDecimal.valueOf(count));
        try {
            accountService.debit(userId, totalPrice);
        } catch (Exception e) {
            log.error("Error reducing stock", e);
            throw e;
        }

        // 创建订单
        String sql = "INSERT INTO orders (user_id, product_id, status) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, userId, productId, "CREATED");

        // 模拟异常，事务应回滚
        if (totalPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("非法金额，事务回滚");
        }
    }
}

