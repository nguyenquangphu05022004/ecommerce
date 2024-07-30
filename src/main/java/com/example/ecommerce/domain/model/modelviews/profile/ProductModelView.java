package com.example.ecommerce.domain.model.modelviews.profile;

import com.example.ecommerce.domain.entities.product.Product;
import lombok.Getter;

@Getter
public class ProductModelView {
    private Long id;
    private String name;

    public ProductModelView(Product p) {
        this.id = p.getId();
        this.name = p.getLanguage().getNameVn();
    }
}