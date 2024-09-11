package com.example.ecommerce.domain.model.modelviews.product;

import com.example.ecommerce.domain.entities.BaseEntity;
import com.example.ecommerce.domain.entities.product.Product;
import com.example.ecommerce.domain.entities.product.ProductBrand;
import com.example.ecommerce.domain.model.modelviews.profile.VendorUserProfileModelView;
import com.example.ecommerce.service.ImageMapper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class ProductModelView extends BaseEntity implements ImageMapper {
    private String name;
    private int minPrice;
    private int maxPrice;
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
        this.minPrice = product.getProductInventories()
                .stream()
                .mapToInt(s -> s.getPrice())
                .min()
                .getAsInt();
        this.maxPrice = product.getProductInventories()
                .stream()
                .mapToInt(s -> s.getPrice())
                .max()
                .getAsInt();
        try {
            this.imageUrl = getImageUrl(product.getProductInventories().get(0).getImages()).get(0);
        } catch (Exception e) {
            this.imageUrl = null;
        }
        this.slug = product.getSlug();
        this.vendor = new VendorUserProfileModelView(product.getVendor());
    }
}
