package com.example.ecommerce.realtime.controller.live.liveproduct.vo;

import com.example.ecommerce.product.controller.spu.self.vo.ProductSpuResVO;
import com.example.ecommerce.product.dal.dataobject.spu.ProductSpu;
import lombok.Data;

@Data
public class LiveProductResVO {
    private ProductSpuResVO productSpu;
    private Boolean display;
}
