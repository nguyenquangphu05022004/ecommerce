package com.example.ecommerce.production.controller.spu.vo.base;

import lombok.Data;

@Data
public class ProductSpuBaseReqVO extends ProductSpuBaseVO{
    private Long productCategoryId;
    private Long productBrandId;
}
