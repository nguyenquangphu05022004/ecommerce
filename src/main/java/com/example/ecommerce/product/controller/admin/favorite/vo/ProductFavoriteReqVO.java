package com.example.ecommerce.product.controller.admin.favorite.vo;

import lombok.Data;

@Data
public class ProductFavoriteReqVO {
    private Long userMemberId;
    private Long productSpuId;
}
