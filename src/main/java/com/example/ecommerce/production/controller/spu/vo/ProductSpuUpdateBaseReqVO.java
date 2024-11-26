package com.example.ecommerce.production.controller.spu.vo;

import com.example.ecommerce.production.controller.spu.vo.base.ProductSpuBaseReqVO;
import lombok.Data;

@Data
public class ProductSpuUpdateBaseReqVO extends ProductSpuBaseReqVO {
    private Long productSpuId;
}
