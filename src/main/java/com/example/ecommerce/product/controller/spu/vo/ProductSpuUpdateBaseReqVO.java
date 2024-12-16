package com.example.ecommerce.product.controller.spu.vo;

import com.example.ecommerce.product.controller.spu.vo.base.ProductSpuBaseReqVO;
import lombok.Data;

@Data
public class ProductSpuUpdateBaseReqVO extends ProductSpuBaseReqVO {
    private Long productSpuId;
}
