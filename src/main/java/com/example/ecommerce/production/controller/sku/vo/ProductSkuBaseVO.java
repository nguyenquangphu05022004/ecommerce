package com.example.ecommerce.production.controller.sku.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
 class ProductSkuBaseVO {
    private Integer price;
    private String image;
    private Integer quantity;
}
