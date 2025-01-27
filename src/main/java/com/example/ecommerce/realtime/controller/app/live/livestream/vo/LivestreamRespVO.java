package com.example.ecommerce.realtime.controller.app.live.livestream.vo;

import com.example.ecommerce.frame.common.date.DateTimeUtils;
import com.example.ecommerce.frame.common.date.DateUtils;
import com.example.ecommerce.frame.common.object.ObjectUtils;
import com.example.ecommerce.realtime.dal.dataobject.live.LiveStream;
import com.example.ecommerce.system.controller.admin.user.vo.SellerResVO;
import com.example.ecommerce.system.controller.app.user.vo.UserMemberResVO;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LivestreamRespVO {
    private Long id;
    private String titleLiveStream;
    private UserMemberResVO hostOwner;
    private Integer totalView;
    private String startDate;
    private Boolean started;

    public LivestreamRespVO(LiveStream liveStream) {
        this.id = liveStream.getId();
        this.titleLiveStream = liveStream.getTitleLiveStream();
        this.hostOwner = new UserMemberResVO(liveStream.getHostOwner());
        this.totalView = liveStream.getTotalView();
        this.started = ObjectUtils.get(liveStream.getStartDate(), t -> {
            return t.isAfter(LocalDateTime.now());
        });
        this.startDate = ObjectUtils.get(liveStream.getStartDate(), DateTimeUtils::format);
    }
}
