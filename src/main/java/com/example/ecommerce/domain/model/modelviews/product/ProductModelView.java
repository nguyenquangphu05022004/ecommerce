package com.example.ecommerce.domain.model.modelviews.product;

import com.example.ecommerce.domain.entities.BaseEntity;
import com.example.ecommerce.domain.entities.file.FileEntityType;
import com.example.ecommerce.domain.entities.product.Product;
import com.example.ecommerce.domain.entities.product.ProductBrand;
import com.example.ecommerce.domain.model.modelviews.profile.VendorUserProfileModelView;
import com.example.ecommerce.service.ImageMapper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Getter
@NoArgsConstructor
@Setter
public class ProductModelView extends BaseEntity implements ImageMapper {
    private String name;
    private int price;
    private String description;
    private CategoryModelView category;
    private ProductBrand productBrand;
    private String imageUrl;
    private String slug;
    private VendorUserProfileModelView vendor;
    public ProductModelView(Product product) {
        this.name = product.getLanguage().getNameVn();
        setId(product.getId());
        this.productBrand = product.getProductBrand();
        this.category = new CategoryModelView(product.getCategory());
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.imageUrl = extractImage(product);
        this.slug = product.getSlug();
        this.vendor = new VendorUserProfileModelView(product.getVendor());
    }

    private String extractImage(Product product) {
        List<String> imageUrls =
                new ProductDetailsViewModel().extractUrlImages(product);
        return imageUrls != null ? imageUrls.get(0) : null;
    }
}
