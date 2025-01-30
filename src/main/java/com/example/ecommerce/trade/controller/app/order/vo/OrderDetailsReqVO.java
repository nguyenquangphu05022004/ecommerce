package com.example.ecommerce.trade.controller.app.order.vo;

import com.example.ecommerce.frame.security.core.utils.SecurityUtils;
import com.example.ecommerce.trade.enums.OrderPlace;
import com.example.ecommerce.trade.enums.PaymentMode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class OrderDetailsReqVO {
    private Long userMemberId = SecurityUtils.getLoginUserMemberId();
    private List<Long> cartIds;
    private List<Long> couponIds;
    private PaymentMode paymentMode;
    private String addressDetails;
    private OrderPlace orderPlace;
}
