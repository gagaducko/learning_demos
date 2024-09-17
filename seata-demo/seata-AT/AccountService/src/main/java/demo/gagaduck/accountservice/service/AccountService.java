package demo.gagaduck.accountservice.service;

import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GlobalTransactional
    public void debit(Long userId, BigDecimal amount) {
        String sql = "UPDATE account SET balance = balance - ? WHERE user_id = ? AND balance >= ?";
        int updatedRows = jdbcTemplate.update(sql, amount, userId, amount);
        if (updatedRows == 0) {
            throw new RuntimeException("余额不足");
        }
    }
}
