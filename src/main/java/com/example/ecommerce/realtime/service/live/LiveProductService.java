package com.example.ecommerce.realtime.service.live;

import com.example.ecommerce.realtime.controller.live.liveproduct.vo.LiveProductReqVO;

public interface LiveProductService {
    void createLiveProduct(LiveProductReqVO reqVO);
    void getListLiveProductByLiveStreamId(Long liveStreamId);
    void updateDisplay(LiveProductReqVO reqVO);
    void deleteLiveProduct(Long liveProductId);
}
