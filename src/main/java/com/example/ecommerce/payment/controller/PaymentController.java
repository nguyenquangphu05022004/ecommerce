package com.example.ecommerce.payment.controller;

import com.example.ecommerce.payment.service.PaymentFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payment")
public class PaymentController {
    private final PaymentFactory paymentFactory;
}
