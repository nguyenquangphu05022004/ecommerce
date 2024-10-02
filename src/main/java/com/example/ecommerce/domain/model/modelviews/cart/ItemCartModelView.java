package com.example.ecommerce.domain.model.modelviews.cart;

import com.example.ecommerce.domain.entities.ProductInventory;
import com.example.ecommerce.domain.model.modelviews.product.ProductInventoryModelView;
import com.example.ecommerce.domain.model.modelviews.product.ProductModelView;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

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
        this.attribute = ProductInventoryModelView.extractAttribute(
                inventory.getProductAttributeMappingValues()
        );
        this.quantity = quantity;
        this.createdAt = LocalDateTime.now();
    }
}
