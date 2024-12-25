package com.example.ecommerce.product.controller.admin.sku.vo;

import lombok.Data;

@Data
public class ProductSkuUpdateStockReqVO {
    private Long productSkuId;
    private int newStock;
}
