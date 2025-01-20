package com.example.ecommerce.trade.controller.app.order.vo;

import com.example.ecommerce.frame.security.core.utils.SecurityUtils;
import com.example.ecommerce.trade.enums.PaymentMode;
import lombok.Data;

@Data
public class Test {
//    private Long userMemberId = SecurityUtils.getLoginUserMemberId();
//    private PaymentMode paymentMode;
    private String addressDetails;
}
