package com.example.ecommerce.product.controller.property.vo;

import com.example.ecommerce.product.dal.dataobject.properties.ProductPropertyValue;
import lombok.Getter;

@Getter
public class ProductPropertyValueResVO {
    private Long id;
    private String value;
    public ProductPropertyValueResVO(ProductPropertyValue p) {
        this.id = p.getId();
        this.value = p.getValue();
    }
}
