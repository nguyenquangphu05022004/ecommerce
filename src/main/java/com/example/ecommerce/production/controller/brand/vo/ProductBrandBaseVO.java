package com.example.ecommerce.production.controller.brand.vo;

import com.example.ecommerce.production.dal.dataobject.brand.ProductBrand;
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
