package com.example.ecommerce.product.controller.admin.property.vo;

import lombok.Data;

@Data
public class ProductPropertyValueReqVO {
    private Long id;
    private Long propertyId;
    private String value;
}
