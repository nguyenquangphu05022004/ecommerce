package com.example.ecommerce.trade.controller.app.order.vo;

import com.example.ecommerce.frame.security.core.utils.SecurityUtils;
import com.example.ecommerce.trade.enums.PaymentMode;
import lombok.Data;

import java.util.Set;

@Data
public class OrderDetailsReqVO {
    private Long userId = SecurityUtils.getLoginUserMemberId();
    private Set<Long> cartIds;
    private PaymentMode paymentMode;
    private String addressDetails;
    private Set<Long> couponIds;
}
