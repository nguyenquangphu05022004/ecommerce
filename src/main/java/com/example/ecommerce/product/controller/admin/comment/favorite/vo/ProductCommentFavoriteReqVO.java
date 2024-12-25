package com.example.ecommerce.product.controller.admin.comment.favorite.vo;

import lombok.Data;

@Data
public class ProductCommentFavoriteReqVO {
    private Long userMemberId;
    private Long commentId;
    private Integer currentLike;
}
