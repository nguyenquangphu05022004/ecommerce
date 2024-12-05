package com.example.ecommerce.product.controller.comment.vo;

import lombok.Data;

@Data
public class PagingProductCommentReqVO {
    private Long productSpuId;
    private Integer currentPage;
}
