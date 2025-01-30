package com.example.ecommerce.realtime.controller.admin.live.product.vo;

import com.example.ecommerce.product.controller.admin.spu.vo.ProductSpuResVO;
import com.example.ecommerce.product.controller.app.spu.vo.AppProductSpuSimpleRespVO;
import com.example.ecommerce.realtime.dal.dataobject.live.LiveProduct;
import lombok.Data;

@Data
public class LiveProductResVO {
    private AppProductSpuSimpleRespVO productSpu;
    private Boolean display;
    private Boolean pin;

    public LiveProductResVO(LiveProduct liveProduct) {
        this.productSpu = new AppProductSpuSimpleRespVO(liveProduct.getProductSpu(), null, null);
        this.display = liveProduct.getDisplay();
        this.pin = liveProduct.getPin();
    }
}
