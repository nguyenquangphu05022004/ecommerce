package com.example.ecommerce.domain.model.modelviews.product;

import com.example.ecommerce.domain.entities.Product;
import com.example.ecommerce.service.ImageMapper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductGalleryModelView extends ProductModelView implements ImageMapper {
    private int rating;//null
    private String imageUrl;
    public ProductGalleryModelView(Product product) {
        super(product);
        try {
            this.imageUrl = getImageUrl(product.getProductInventories().get(0).getImages()).get(0);
        } catch (Exception e) {
            this.imageUrl = null;
        }
    }
}
