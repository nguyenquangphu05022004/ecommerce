package com.example.ecommerce.domain.model.modelviews.product;

import com.example.ecommerce.domain.entities.BaseEntity;
import com.example.ecommerce.domain.entities.ProductAttributeMappingValue;
import com.example.ecommerce.domain.entities.ProductInventory;
import com.example.ecommerce.service.ImageMapper;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class ProductInventoryModelView extends BaseEntity implements ImageMapper {
    private ProductModelView productModelView;
    private String attributeCombinationProduct;
    private Integer quantity;
    private String skuCode;
    private Integer numberOfProductSold;
    private String imageUrl;
    public ProductInventoryModelView(ProductInventory p) {
        this.productModelView = new ProductModelView(p.getProduct());
        this.attributeCombinationProduct = extractAttribute(p.getProductAttributeMappingValues());
        this.quantity = p.getQuantity();
        this.skuCode = p.getSkuCode();
        this.numberOfProductSold = p.getNumberOfProductSold();
        if(p.getImages() != null && p.getImages().size() >0 ) {
            this.imageUrl = getImageUrl(p.getImages()).get(0);
        }
        setId(p.getId());
    }

    public static String extractAttribute(
            List<ProductAttributeMappingValue> productAttributeMappingValues
    ) {
        return productAttributeMappingValues
                .stream()
                .map(s -> {
                    return s.getProductAttribute() + ": " + s.getValue();
                })
                .collect(Collectors.joining(", "));
    }

}
