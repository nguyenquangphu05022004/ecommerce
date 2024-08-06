package com.example.ecommerce.event.listener;

import com.example.ecommerce.domain.entities.product.Product;
import com.example.ecommerce.domain.entities.product.ProductInventory;
import com.example.ecommerce.event.Observer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ProductListener {

    public  Observer<Product> createProductListener() {
        return product -> {
            log.info(String.format("Vendor: %s created product with name: %s",product.getVendor().getShopName(), product.getLanguage().getNameVn()));
        };
    }
    public  Observer<ProductInventory> updateQuantityProduct() {
        return inventory -> {
            log.info(String.format("Product: %s is updated quantity to %s",
                    inventory.getProduct().getLanguage().getNameVn(), inventory.getQuantity()));
        };
    }
}
