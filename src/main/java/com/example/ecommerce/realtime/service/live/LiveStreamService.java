package com.example.ecommerce.realtime.service.live;

import com.example.ecommerce.realtime.controller.app.live.livestream.vo.LiveStreamCreateReqVO;
import com.example.ecommerce.realtime.controller.app.live.livestream.vo.LivestreamRespVO;
import com.example.ecommerce.realtime.dal.dataobject.live.LiveStream;

import java.util.List;

public interface LiveStreamService  {
    LiveStream createLiveStream(LiveStreamCreateReqVO reqVO);


    /**
     * Delete this livestream
     * @param liveStreamId
     */
    void closeLiveStream(Long liveStreamId);
    void deleteLivestream(Long liveStreamId);
    List<LivestreamRespVO> getListLivestream();

    LiveStream getLiveStreamById(Long id);
}
