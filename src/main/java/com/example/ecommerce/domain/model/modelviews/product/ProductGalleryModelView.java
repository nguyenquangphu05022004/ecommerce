package com.example.ecommerce.domain.model.modelviews.product;

import com.example.ecommerce.domain.entities.product.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductGalleryModelView extends ProductModelView {
    private int rating;//null
    public ProductGalleryModelView(Product product) {
        super(product);
    }
}
