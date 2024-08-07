package com.example.ecommerce.domain.model.modelviews.product;

import com.example.ecommerce.domain.entities.BaseEntity;
import com.example.ecommerce.domain.entities.file.FileEntityType;
import com.example.ecommerce.domain.entities.product.Product;
import com.example.ecommerce.domain.entities.product.ProductBrand;
import com.example.ecommerce.domain.model.modelviews.profile.VendorModelView;
import com.example.ecommerce.service.mapper.ImageMapper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

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
    private VendorModelView vendor;
    public ProductModelView(Product product) {
        this.name = product.getLanguage().getNameVn();
        setId(product.getId());
        this.productBrand = product.getProductBrand();
        this.category = new CategoryModelView(product.getCategory());
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.imageUrl = CollectionUtils.isEmpty(product.getImages()) ? null : getImageUrl(FileEntityType.PRODUCT.name(), product.getImages().get(0));
        this.slug = product.getSlug();
        this.vendor = new VendorModelView(product.getVendor());
    }
}
