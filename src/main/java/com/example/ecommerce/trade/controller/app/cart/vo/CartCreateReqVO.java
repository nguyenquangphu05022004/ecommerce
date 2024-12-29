package com.example.ecommerce.trade.controller.app.cart.vo;

import lombok.Data;

@Data
public class CartCreateReqVO {
    private Long productSkuId;
    private Integer quantity;
}
