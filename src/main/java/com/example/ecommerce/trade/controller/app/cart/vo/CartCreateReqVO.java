package com.example.ecommerce.trade.controller.app.cart.vo;

import com.example.ecommerce.frame.security.core.utils.SecurityUtils;
import lombok.Data;

@Data
public class CartCreateReqVO {
    private Long productSkuId;
    private Integer quantity;

}
