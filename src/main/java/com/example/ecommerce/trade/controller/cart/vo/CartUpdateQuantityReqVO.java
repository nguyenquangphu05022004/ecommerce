package com.example.ecommerce.trade.controller.cart.vo;

import lombok.Data;

@Data
public class CartUpdateQuantityReqVO {
    private Long cartId;
    private char operand;
}
