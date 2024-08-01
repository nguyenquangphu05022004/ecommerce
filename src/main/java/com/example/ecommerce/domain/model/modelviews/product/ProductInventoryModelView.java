package com.example.ecommerce.domain.model.modelviews.product;

import com.example.ecommerce.common.utils.SystemUtils;
import com.example.ecommerce.domain.entities.product.ProductInventory;
import lombok.Getter;

import java.util.Arrays;
import java.util.stream.Collectors;
@Getter
public class ProductInventoryModelView {
    private ProductModelView productModelView;
    private String attributeProduct;
    private Integer quantity;
    private String skuCode;
    public ProductInventoryModelView(ProductInventory p) {
        this.productModelView = new ProductModelView(p.getProduct());
        this.attributeProduct = extractAttribute(p.getAttributeCombinationKey());
        this.quantity = p.getQuantity();
        this.skuCode = p.getSkuCode();
    }

    private String extractAttribute(String attributeCombinationKey) {
        return Arrays.stream(attributeProduct.split(SystemUtils.SEPARATE))
                .map(pair -> pair.split(":")[1])
                .collect(Collectors.joining(", "));
    }
}
