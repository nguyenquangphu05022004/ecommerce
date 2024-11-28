package com.example.ecommerce.production.controller.brand.vo;

import com.example.ecommerce.production.dal.dataobject.brand.ProductBrand;
import lombok.Data;

@Data
public class ProductBrandResVO extends ProductBrandBaseVO{
    private Long id;

    public ProductBrandResVO(ProductBrand productBrand) {
        super(productBrand);
        this.id = productBrand.getId();
    }
}
