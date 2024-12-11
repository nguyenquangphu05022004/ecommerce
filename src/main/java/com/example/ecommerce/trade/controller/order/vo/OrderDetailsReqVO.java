package com.example.ecommerce.trade.controller.order.vo;

import com.example.ecommerce.trade.dal.dataobject.order.PaymentMode;
import lombok.Data;

import java.util.Set;

@Data
public class OrderDetailsReqVO {
    private Set<Long> cartIds;
    private Long addressId;
    private PaymentMode paymentMode;
}
