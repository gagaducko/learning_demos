package demo.gagaduck.productservice.service;

import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GlobalTransactional
    public void reduceStock(Long productId, int amount) {
        String sql = "UPDATE product SET stock = stock - ? WHERE id = ? AND stock >= ?";
        int updatedRows = jdbcTemplate.update(sql, amount, productId, amount);
        if (updatedRows == 0) {
            throw new RuntimeException("库存不足");
        }
    }
}
