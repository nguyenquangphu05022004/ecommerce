package com.example.ecommerce.trade.dal.dataobject.order;

import com.example.ecommerce.product.dal.dataobject.sku.ProductSku;
import lombok.Getter;

import java.beans.Transient;

@Getter
public class OrderItem {
    private ProductSku productSku;
    private Order order;

    private Integer quantity;


    @Transient
    public Integer totalPrice() {
        return productSku.getPrice() * quantity;
    }
}
