package com.example.ecommerce.payment.vo;

import lombok.Data;

@Data
public class OrderPaymentReqVO extends PaymentReqVO {
    private Long orderId;
}
