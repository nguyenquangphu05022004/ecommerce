package com.example.ecommerce.production.controller.sku.vo;

import com.example.ecommerce.production.dal.dataobject.sku.ProductSku;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
 class ProductSkuBaseVO {
    private Integer price;
    private String image;
    private Integer quantity;

    public ProductSkuBaseVO(ProductSku productSku) {
        this.price = productSku.getPrice();
        this.image = productSku.getImage();
        this.quantity = productSku.getQuantity();
    }
}
