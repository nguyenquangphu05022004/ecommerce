package com.example.ecommerce.product.controller.spu.self.vo.base;

import lombok.Data;

@Data
public class ProductSpuBaseReqVO extends ProductSpuBaseVO{
    private Long productCategoryId;
    private Long productBrandId;
}
