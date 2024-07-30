package com.example.ecommerce.domain.model.modelviews.product;

import com.example.ecommerce.domain.entities.BaseEntity;
import com.example.ecommerce.domain.entities.file.EntityType;
import com.example.ecommerce.domain.entities.product.Category;
import com.example.ecommerce.domain.entities.product.Product;
import com.example.ecommerce.domain.entities.product.ProductBrand;
import com.example.ecommerce.service.mapper.ImageMapper;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductGalleryModelView extends BaseEntity  implements ImageMapper {
    private String name;
    private int price;
    private String description;
    private Category category;
    private ProductBrand productBrand;
    private String imageUrl;
    private int rating;
    public ProductGalleryModelView(Product product) {
        this.name = product.getLanguage().getNameVn();
        setId(product.getId());
        this.productBrand = product.getProductBrand();
        this.category = product.getCategory();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.imageUrl = getImageUrl(EntityType.PRODUCT.name(),  product.getImages().get(0));
    }
}
