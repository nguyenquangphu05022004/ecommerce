package com.example.ecommerce.realtime.service.live;

import com.example.ecommerce.realtime.controller.live.livestream.vo.LiveStreamCreateReqVO;

public interface LiveStreamService  {
    void createLiveStream(LiveStreamCreateReqVO reqVO);
    void startLiveStream(Long liveStreamId);
    void closeLiveStream(Long liveStreamId);
    void randomListLiveStream();
}
