package com.example.ecommerce.product.controller.sku.vo;

import com.example.ecommerce.product.dal.dataobject.sku.ProductSku;
import lombok.Data;

@Data
public class ProductSkuSimpleRespVO {
    private Long id;
    private Integer price;
    private String image;
    private Integer quantity;

    public ProductSkuSimpleRespVO(ProductSku sku) {
        this.id = sku.getId();
        this.price = sku.getPrice();
        this.image = sku.getImage();
        this.quantity = sku.getQuantity();
    }
}
