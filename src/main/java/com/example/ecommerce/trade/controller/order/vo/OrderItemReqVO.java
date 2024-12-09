package com.example.ecommerce.trade.controller.order.vo;

import lombok.Data;

@Data
public class OrderItemReqVO {
    private Long productSkuId;
    private Integer quantity;
}
