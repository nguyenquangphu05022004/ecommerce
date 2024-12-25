package com.example.ecommerce.product.controller.admin.sku.vo.property;

import lombok.Data;

@Data
 class BaseReqVO {
    private Long productSkuId;
    private Long propertyId;
    private Long propertyValueId;
}
