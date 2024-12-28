package com.example.ecommerce.realtime.service.live;

import com.example.ecommerce.realtime.controller.admin.live.livestream.vo.LiveStreamCreateReqVO;
import com.example.ecommerce.realtime.dal.dataobject.live.LiveStream;

public interface LiveStreamService  {
    LiveStream createLiveStream(LiveStreamCreateReqVO reqVO);

    void startLiveStream(Long liveStreamId);

    /**
     * Delete this livestream
     * @param liveStreamId
     */
    void closeLiveStream(Long liveStreamId);

    void randomListLiveStream();

    LiveStream getLiveStreamById(Long id);
}
