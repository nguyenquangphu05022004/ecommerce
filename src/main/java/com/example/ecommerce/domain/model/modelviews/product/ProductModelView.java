package com.example.ecommerce.domain.model.modelviews.product;

import com.example.ecommerce.domain.entities.BaseEntity;
import com.example.ecommerce.domain.entities.product.Product;
import com.example.ecommerce.domain.entities.product.ProductBrand;
import com.example.ecommerce.domain.model.modelviews.profile.VendorUserProfileModelView;
import com.example.ecommerce.service.ImageMapper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import java.util.Collections;

@Getter
@NoArgsConstructor
@Setter
public class ProductModelView extends BaseEntity {
    private String nameVn;
    private String nameEn;
    private int minPrice;
    private int maxPrice;
    private String description;
    private CategoryModelView category;
    private ProductBrand productBrand;
    private String slug;
    private VendorUserProfileModelView vendor;

    public ProductModelView(Product product) {
        if (product != null) {
            this.nameEn = product.getNameEn();
            this.nameVn = product.getNameVn();
            setId(product.getId());
            this.productBrand = product.getProductBrand();
            this.category = new CategoryModelView(product.getCategory());
            this.description = product.getDescription();
            if (!CollectionUtils.isEmpty(product.getProductInventories())) {
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
            }
            this.slug = product.getSlug();
            this.vendor = new VendorUserProfileModelView(product.getVendor());
        }
    }
}
