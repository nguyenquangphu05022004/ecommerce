package com.example.ecommerce.product.controller.admin.favorite.vo;

import lombok.Data;

@Data
public class SellerFavoriteReqVO {
    private Long userMemberId;
    private Long sellerId;
}
