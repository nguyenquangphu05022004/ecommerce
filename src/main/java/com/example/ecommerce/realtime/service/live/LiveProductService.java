package com.example.ecommerce.realtime.service.live;

import com.example.ecommerce.realtime.controller.live.liveproduct.vo.LiveProductReqVO;
import com.example.ecommerce.realtime.dal.dataobject.live.LiveProduct;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

public interface LiveProductService {
    void createLiveProduct(Long productSkuId);

    @Async
    void updateLiveProductDisplay(Long productLiveId, Long liveStreamId);

    LiveProduct updatePinLiveProduct(Long productLiveId, Boolean pin);

    LiveProduct getLiveProductById(Long productLiveId);

    List<LiveProduct> getListLiveProductByLiveStreamId(Long liveStreamId);
    void deleteLiveProduct(Long liveProductId);
}
