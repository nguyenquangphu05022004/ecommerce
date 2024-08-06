package com.example.ecommerce.domain.model.modelviews.product;

import com.example.ecommerce.domain.entities.BaseEntity;
import com.example.ecommerce.domain.entities.file.FileEntityType;
import com.example.ecommerce.domain.entities.product.Category;
import com.example.ecommerce.domain.entities.product.Product;
import com.example.ecommerce.domain.entities.product.ProductBrand;
import com.example.ecommerce.service.mapper.ImageMapper;
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
