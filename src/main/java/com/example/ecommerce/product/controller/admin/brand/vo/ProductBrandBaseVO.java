package com.example.ecommerce.product.controller.admin.brand.vo;

import com.example.ecommerce.product.dal.dataobject.brand.ProductBrand;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductBrandBaseVO {
    private String name;
    private String slug;
    private String thumbnail;

    public ProductBrandBaseVO(ProductBrand s) {
        this.name = s.getName();
        this.slug = s.getSlug();
        this.thumbnail = s.getAvatar();
    }
}
