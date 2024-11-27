package com.example.ecommerce.production.controller.property.vo;

import lombok.Data;

@Data
public class ProductPropertyValueReqVO {
    private Long id;
    private Long propertyId;
    private String value;
}
