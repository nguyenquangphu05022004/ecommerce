package com.example.ecommerce.production.controller.sku.vo.property;

import lombok.Data;

@Data
 class BaseReqVO {
    private Long productSkuId;
    private Long propertyId;
    private Long propertyValueId;

    /**
     * Options when property not exists
     */
    private String propertyName;
    private String propertyValue;
}
