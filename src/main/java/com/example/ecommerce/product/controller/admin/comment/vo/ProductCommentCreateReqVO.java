package com.example.ecommerce.product.controller.admin.comment.vo;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ProductCommentCreateReqVO {
    private Long id;
    private Long userMemberId;
    private Long productSkuId;
    private Long productSpuId;
    private Map<Long, String> evaluations;
    private List<String> imageUrls;
    private String content;
    private Double rating;
    private Long replyCommentId;
}
