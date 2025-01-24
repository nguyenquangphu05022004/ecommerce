package com.example.ecommerce.product.controller.admin.spu.vo.info;

import lombok.Data;

@Data
public class ProductSpuInfoCreateReqVO {
    private Long propertyId;
    private String value;
    private Long spuId;
}
