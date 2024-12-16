package com.example.ecommerce.product.controller.property.vo;

import com.example.ecommerce.product.dal.dataobject.properties.ProductProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class ProductPropertyVO {
    private Long id;
    private String name;
    public ProductPropertyVO(ProductProperty p) {
        this.id = p.getId();
        this.name = p.getName();
    }

}
