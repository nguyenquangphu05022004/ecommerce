package com.example.ecommerce.domain.model.modelviews.product;

import com.example.ecommerce.domain.entities.product.ProductBrand;

public class BrandModelView {
    private long id;
    private String name;
    private String slug;
    public BrandModelView(ProductBrand br) {
        this.id = br.getId();
        this.name = br.getName();
        this.slug = br.getSlug();
    }
}
