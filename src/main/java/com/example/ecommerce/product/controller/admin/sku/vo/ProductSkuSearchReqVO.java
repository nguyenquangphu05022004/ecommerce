package com.example.ecommerce.product.controller.admin.sku.vo;

import lombok.Data;

import java.util.Map;

@Data
public class ProductSkuSearchReqVO {
    private Map<Long, Long> properties;
    private Long productSpuId;
}
