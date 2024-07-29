package com.example.ecommerce.domain.model.modelviews.profile;

import com.example.ecommerce.domain.Product;
import lombok.Getter;

@Getter
public class ProductModelView {
    private Long id;
    private String name;

    protected ProductModelView(Product p) {
        this.id = p.getId();
        this.name = p.getLanguage().getNameVn();
    }
}
