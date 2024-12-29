package com.example.ecommerce.trade.controller.app.order.vo;

import com.example.ecommerce.trade.enums.PaymentMode;
import lombok.Data;

import java.util.Set;

@Data
public class OrderDetailsReqVO {
    private Long userId;
    private Set<Long> cartIds;
    private PaymentMode paymentMode;
    private String addressDetails;
    private Set<Long> couponIds;
}
