package com.example.ecommerce.production.controller.category.vo;

import lombok.Data;

@Data
public class ProductCategoryBaseReqVO {
    private String name;
    private Long categoryParentId;
    private String thumbnail;
}
