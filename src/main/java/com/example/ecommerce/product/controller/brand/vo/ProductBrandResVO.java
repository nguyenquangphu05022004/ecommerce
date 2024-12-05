package com.example.ecommerce.product.controller.brand.vo;

import com.example.ecommerce.product.dal.dataobject.brand.ProductBrand;
import lombok.Data;

@Data
public class ProductBrandResVO extends ProductBrandBaseVO{
    private Long id;

    public ProductBrandResVO(ProductBrand productBrand) {
        super(productBrand);
        this.id = productBrand.getId();
    }
}
