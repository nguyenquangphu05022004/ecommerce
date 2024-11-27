package com.example.ecommerce.production.controller.property.vo;

import com.example.ecommerce.production.dal.dataobject.properties.ProductProperty;
import lombok.Getter;

@Getter
public class ProductPropertyVO {
    private Long id;
    private String name;
    public ProductPropertyVO(ProductProperty p) {
        this.id = p.getId();
        this.name = p.getName();
    }
}
