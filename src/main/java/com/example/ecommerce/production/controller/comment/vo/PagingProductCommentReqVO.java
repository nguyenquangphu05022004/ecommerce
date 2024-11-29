package com.example.ecommerce.production.controller.comment.vo;

import lombok.Data;

@Data
public class PagingProductCommentReqVO {
    private Long productSpuId;
    private Integer currentPage;
    private Integer totalPage;
}
