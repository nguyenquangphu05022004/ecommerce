package com.example.ecommerce.product.controller.admin.category.vo;

import lombok.Data;

@Data
public class ProductCategoryBaseReqVO {
    private String name;
    private Long categoryParentId;
    private String thumbnail;
}
