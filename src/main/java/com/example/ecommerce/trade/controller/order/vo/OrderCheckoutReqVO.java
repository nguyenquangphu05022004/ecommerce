package com.example.ecommerce.trade.controller.order.vo;

import lombok.Data;

import java.util.Set;

@Data
public class OrderCheckoutReqVO {
    private Set<Long> cartIds;
    private Set<String> couponCodes;
}
