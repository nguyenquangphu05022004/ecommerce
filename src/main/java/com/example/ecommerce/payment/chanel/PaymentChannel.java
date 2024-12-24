package com.example.ecommerce.payment.chanel;

import org.springframework.scheduling.annotation.Async;

import java.util.Map;

public interface PaymentChannel {


    default Object doPayment(Map<String, Object> params) {return null;}

}
