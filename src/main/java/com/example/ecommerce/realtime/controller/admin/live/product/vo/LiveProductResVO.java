package com.example.ecommerce.realtime.controller.admin.live.product.vo;

import com.example.ecommerce.product.controller.admin.spu.vo.ProductSpuResVO;
import com.example.ecommerce.realtime.dal.dataobject.live.LiveProduct;
import lombok.Data;

@Data
public class LiveProductResVO {
    private ProductSpuResVO productSpu;
    private Boolean display;
    private Boolean pin;

    public LiveProductResVO(LiveProduct liveProduct) {
        this.productSpu = new ProductSpuResVO(liveProduct.getProductSpu());
        this.display = liveProduct.getDisplay();
        this.pin = liveProduct.getPin();
    }
}
