package com.example.ecommerce.domain.model.modelviews.cart;

import com.example.ecommerce.common.utils.SystemUtils;
import com.example.ecommerce.domain.entities.product.ProductInventory;
import com.example.ecommerce.domain.model.modelviews.product.ProductModelView;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@Setter
public class ItemCartModelView {
    private ProductModelView product;
    private String attribute;
    private int quantity;
    private LocalDateTime createdAt;
    public ItemCartModelView(ProductInventory inventory, int quantity) {
        this.product = new ProductModelView(inventory.getProduct());
        this.attribute = extractAttribute(inventory.getAttributeCombinationKey());
        this.quantity = quantity;
        this.createdAt = LocalDateTime.now();
    }

    private String extractAttribute(String attributeCombinationKey) {
        return Arrays.stream(attributeCombinationKey.split(SystemUtils.SEPARATE))
                .map(pair -> pair.split(":")[1])
                .collect(Collectors.joining(", "));
    }
}
