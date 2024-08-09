package com.example.ecommerce.domain.model.modelviews.product;

import com.example.ecommerce.common.utils.SystemUtils;
import com.example.ecommerce.domain.entities.BaseEntity;
import com.example.ecommerce.domain.entities.product.ProductInventory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;
import java.util.stream.Collectors;
@Getter
@Setter
@NoArgsConstructor
public class ProductInventoryModelView extends BaseEntity {
    private ProductModelView productModelView;
    private String attributeCombinationProduct;
    private Integer quantity;
    private String skuCode;
    private Integer numberOfProductSold;
    public ProductInventoryModelView(ProductInventory p) {
        this.productModelView = new ProductModelView(p.getProduct());
        this.attributeCombinationProduct = extractAttribute(p.getAttributeCombinationKey());
        this.quantity = p.getQuantity();
        this.skuCode = p.getSkuCode();
        this.numberOfProductSold = p.getNumberOfProductSold();
        setId(p.getId());
    }

    private String extractAttribute(String attributeCombinationKey) {
        return Arrays.stream(attributeCombinationKey.split(SystemUtils.SEPARATE))
                .collect(Collectors.joining(", "));
    }
}
