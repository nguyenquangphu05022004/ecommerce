package com.example.ecommerce.trade.controller.order.vo;

import com.example.ecommerce.trade.dal.dataobject.order.PaymentMode;
import lombok.Data;

import java.util.Set;

@Data
public class OrderDetailsReqVO {
    private OrderCheckout orderCheckout;
    private PaymentMode paymentMode;
    private Long addressId;
}
