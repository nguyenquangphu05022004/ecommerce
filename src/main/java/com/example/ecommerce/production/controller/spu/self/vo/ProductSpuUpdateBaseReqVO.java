package com.example.ecommerce.production.controller.spu.self.vo;

import com.example.ecommerce.production.controller.spu.self.vo.base.ProductSpuBaseReqVO;
import lombok.Data;

@Data
public class ProductSpuUpdateBaseReqVO extends ProductSpuBaseReqVO {
    private Long productSpuId;
}
