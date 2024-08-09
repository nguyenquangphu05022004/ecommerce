package com.example.ecommerce.domain.model.modelviews.order;

import com.example.ecommerce.domain.entities.order.Item;
import com.example.ecommerce.domain.model.modelviews.product.ProductInventoryModelView;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class ItemViewModel {
    private Integer quantity;
    private ProductInventoryModelView productInventory;

    public ItemViewModel(Item item) {
        this.quantity = item.getQuantity();
        this.productInventory = new ProductInventoryModelView(item.getProductInventory());
    }

}
