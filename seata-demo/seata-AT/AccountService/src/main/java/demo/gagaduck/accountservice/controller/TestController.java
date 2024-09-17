package demo.gagaduck.accountservice.controller;

import demo.gagaduck.accountservice.service.AccountService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class TestController {

    @Resource
    private AccountService accountService;

    @PostMapping("/debit")
    public void debit(@RequestParam("userId") Long userId,
                            @RequestParam("amount") BigDecimal amount) {
        accountService.debit(userId, amount);
    }


}
