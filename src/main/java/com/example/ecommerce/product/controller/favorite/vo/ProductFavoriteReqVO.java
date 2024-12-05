package com.example.ecommerce.product.controller.favorite.vo;

import lombok.Data;

@Data
public class ProductFavoriteReqVO {
    private Long userMemberId;
    private Long productSpuId;
}
