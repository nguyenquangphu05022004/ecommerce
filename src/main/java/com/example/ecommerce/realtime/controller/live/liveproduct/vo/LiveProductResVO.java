package com.example.ecommerce.realtime.controller.live.liveproduct.vo;

import com.example.ecommerce.product.controller.admin.spu.vo.ProductSpuResVO;
import lombok.Data;

@Data
public class LiveProductResVO {
    private ProductSpuResVO productSpu;
    private Boolean display;
}
