package com.example.ecommerce.trade.controller.cart.vo;

import lombok.Data;

@Data
public class CartCreateReqVO {
    private Long productSkuId;
    private Integer quantity;
}
