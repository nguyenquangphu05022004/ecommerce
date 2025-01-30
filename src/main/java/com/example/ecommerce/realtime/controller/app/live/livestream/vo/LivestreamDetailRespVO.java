package com.example.ecommerce.realtime.controller.app.live.livestream.vo;

import com.example.ecommerce.realtime.controller.admin.live.product.vo.LiveProductResVO;
import com.example.ecommerce.realtime.dal.dataobject.live.LiveStream;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
public class LivestreamDetailRespVO extends LivestreamRespVO{

    private List<LiveProductResVO> liveProducts;
    public LivestreamDetailRespVO(LiveStream liveStream) {
        super(liveStream);
    }

}
