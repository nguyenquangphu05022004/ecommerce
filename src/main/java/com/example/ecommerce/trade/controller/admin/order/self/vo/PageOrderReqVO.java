package com.example.ecommerce.trade.controller.admin.order.self.vo;

import com.example.ecommerce.frame.common.pojo.PageParam;
import com.example.ecommerce.trade.enums.OrderPlace;
import com.example.ecommerce.trade.enums.OrderStatus;
import com.example.ecommerce.trade.enums.PaymentMode;
import com.example.ecommerce.trade.enums.PaymentStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
public class PageOrderReqVO extends PageParam {
    private LocalDateTime start;
    private LocalDateTime end;

    private Boolean combinationOfSellers;
    private OrderStatus orderStatus;
    private OrderPlace orderPlace;
    private PaymentMode paymentMode;
    private PaymentStatus paymentStatus;

}
