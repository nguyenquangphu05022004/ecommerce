package com.example.ecommerce.product.controller.admin.comment.vo;

import lombok.Data;

import java.util.Map;

@Data
public class ProductCommentUpdateReqVO {
    private Long id;
    private String content;
    private Double rating;
    private Map<Long, String> mapProperties;

}
