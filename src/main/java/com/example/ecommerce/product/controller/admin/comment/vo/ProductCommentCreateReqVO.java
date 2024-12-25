package com.example.ecommerce.product.controller.admin.comment.vo;

import lombok.Data;

import java.util.Map;

@Data
public class ProductCommentCreateReqVO {
    private Long userMemberId;
    private Long productSkuId;
    private Long productSpuId;
    /**
     * Key: ProductPropertyId
     * Value: Value
     */
    private Map<Long, String> mapProperties;

    private String content;
    private Double rating;
    private Long replyCommentId;
}
