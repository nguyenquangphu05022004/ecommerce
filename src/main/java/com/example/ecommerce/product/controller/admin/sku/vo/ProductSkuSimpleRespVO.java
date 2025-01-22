package com.example.ecommerce.product.controller.admin.sku.vo;

import com.example.ecommerce.product.dal.dataobject.sku.ProductSku;
import lombok.Data;

@Data
public class ProductSkuSimpleRespVO {
    private Long id;
    private Integer price;
    private String image;
    private Integer quantity;
    private String name;
    private String properties;
    public ProductSkuSimpleRespVO(ProductSku sku) {
        this.id = sku.getId();
        this.price = sku.getPrice();
        this.image = sku.getImage();
        this.quantity = sku.getQuantity();
        this.name = sku.getProductSpu().getName();
        this.properties = sku.toProperties();
    }
}
