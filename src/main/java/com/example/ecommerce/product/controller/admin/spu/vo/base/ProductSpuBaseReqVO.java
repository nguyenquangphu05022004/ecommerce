package com.example.ecommerce.product.controller.admin.spu.vo.base;

import lombok.Data;

@Data
public class ProductSpuBaseReqVO extends ProductSpuBaseVO{
    private Long userId;
    private Long productCategoryId;
    private Long productBrandId;
}
