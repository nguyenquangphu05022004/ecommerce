package com.example.ecommerce.trade.controller.order.vo;

import com.example.ecommerce.trade.dal.dataobject.order.PaymentMode;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class OrderDetailsReqVO {
    private Long userId;
    private Set<Long> cartIds;
    private PaymentMode paymentMode;
    private String addressDetails;
    private Set<Long> couponIds;
}
