package com.example.ecommerce.production.controller.spu.vo.base;

import com.example.ecommerce.production.dal.dataobject.spu.ProductSpu;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductSpuBaseVO {
    private String name;
    private String description;
    private Integer minPrice;
    private Integer maxPrice;

    public ProductSpuBaseVO(ProductSpu productSpu) {
        this.name = productSpu.getName();
        this.description = productSpu.getDescription();
        this.maxPrice = productSpu.getMaxPrice();
        this.maxPrice = productSpu.getMaxPrice();
    }
}
