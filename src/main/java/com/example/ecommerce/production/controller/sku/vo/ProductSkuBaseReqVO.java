package com.example.ecommerce.production.controller.sku.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
 class ProductSkuBaseReqVO extends ProductSkuBaseVO{
    private Long productSpuId;
}
