package com.example.ecommerce.production.controller.comment.vo;

import lombok.Data;

import java.util.Map;

@Data
public class ProductCommentUpdateReqVO {
    private Long id;
    private String content;
    private Long rating;
    private Map<Long, String> mapProperties;

}
