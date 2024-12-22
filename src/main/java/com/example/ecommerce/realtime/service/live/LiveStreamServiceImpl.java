package com.example.ecommerce.realtime.service.live;

import com.example.ecommerce.realtime.controller.live.livestream.vo.LiveStreamCreateReqVO;
import com.example.ecommerce.realtime.dal.dataobject.live.LiveStream;
import com.example.ecommerce.realtime.dal.repo.live.LiveStreamRepository;
import com.example.ecommerce.system.dal.dataobject.user.UserMember;
import com.example.ecommerce.system.service.user.UserMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static com.example.ecommerce.frame.common.exception.utils.ServiceExceptionUtils.exception;
import static com.example.ecommerce.realtime.constants.ErrorCodeConstants.LIVE_STREAM_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Validated
public class LiveStreamServiceImpl implements LiveStreamService{
    private final LiveStreamRepository liveStreamRepository;
    private final UserMemberService userMemberService;

    @Override
    public LiveStream createLiveStream(LiveStreamCreateReqVO reqVO) {
        UserMember userMember = this.userMemberService.getUserMemberById(reqVO.getUserId());

        LiveStream liveStreamRoom = LiveStream.builder()
                .totalViewLive(0).totalView(0)
                .started(false).titleLiveStream(reqVO.getTitle())
                .hostOwner(userMember).build();

        this.liveStreamRepository.save(liveStreamRoom);

        return liveStreamRoom;
    }

    @Override
    public void startLiveStream(Long liveStreamId) {
        LiveStream liveStream = getLiveStreamById(liveStreamId);
        liveStream.setStarted(true);
        this.liveStreamRepository.save(liveStream);
    }

    @Override
    public void closeLiveStream(Long liveStreamId) {
        LiveStream liveStream = getLiveStreamById(liveStreamId);

    }



    @Override
    public void randomListLiveStream() {

    }

    @Override
    public LiveStream getLiveStreamById(Long id) {
        return this.liveStreamRepository.findById(id)
                .orElseThrow(() -> exception(LIVE_STREAM_NOT_FOUND));
    }
}
