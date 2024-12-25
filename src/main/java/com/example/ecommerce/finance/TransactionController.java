package com.example.ecommerce.finance;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin-api/finance/transactions")
public class TransactionController {
    @GetMapping("/test")
    public String test() {
        System.out.println("-----------------why----------------");
        return "hello";
    }
}
