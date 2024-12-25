package com.example.ecommerce.product.controller.admin.property.vo;

import com.example.ecommerce.product.dal.dataobject.properties.ProductPropertyValue;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class ProductPropertyValueResVO {
    private Long id;
    private String value;
    public ProductPropertyValueResVO(ProductPropertyValue p) {
        this.id = p.getId();
        this.value = p.getPropertyValue();
    }
}
