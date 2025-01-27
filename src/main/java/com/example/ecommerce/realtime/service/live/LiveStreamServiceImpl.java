package com.example.ecommerce.realtime.service.live;

import com.example.ecommerce.frame.common.collection.CollUtils;
import com.example.ecommerce.realtime.controller.app.live.livestream.vo.LiveStreamCreateReqVO;
import com.example.ecommerce.realtime.controller.app.live.livestream.vo.LivestreamRespVO;
import com.example.ecommerce.realtime.dal.dataobject.live.LiveStream;
import com.example.ecommerce.realtime.dal.repo.live.LiveProductRepository;
import com.example.ecommerce.realtime.dal.repo.live.LiveStreamRepository;
import com.example.ecommerce.realtime.dal.repo.live.LiveCommentRepository;
import com.example.ecommerce.system.dal.dataobject.user.UserMember;
import com.example.ecommerce.system.service.user.UserMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static com.example.ecommerce.frame.common.exception.utils.ServiceExceptionUtils.exception;
import static com.example.ecommerce.realtime.constants.ErrorCodeConstants.LIVE_STREAM_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Validated
public class LiveStreamServiceImpl implements LiveStreamService{
    private final LiveStreamRepository liveStreamRepository;
    private final UserMemberService userMemberService;
    private final LiveCommentRepository liveCommentRepository;
    private final LiveProductRepository liveProductRepository;
    @Override
    public LiveStream createLiveStream(LiveStreamCreateReqVO reqVO) {
        UserMember userMember = this.userMemberService.getUserMemberById(reqVO.getUserId());

        LiveStream liveStreamRoom = LiveStream.builder()
                .totalView(0)
                .startDate(reqVO.getStartDate()).titleLiveStream(reqVO.getTitle())
                .hostOwner(userMember).build();

        this.liveStreamRepository.save(liveStreamRoom);

        return liveStreamRoom;
    }



    @Override
    public void closeLiveStream(Long liveStreamId) {
        LiveStream liveStream = getLiveStreamById(liveStreamId);
        liveStream.setIsClosed(true);
        this.liveStreamRepository.save(liveStream);

        //send to ui for remove tab livestream
    }

    @Override
    public void deleteLivestream(Long liveStreamId) {
        liveCommentRepository.deleteAllByLiveStreamId(liveStreamId);
        liveProductRepository.deleteAllByLiveStreamId(liveStreamId);
        this.liveStreamRepository.deleteById(liveStreamId);
    }


    @Override
    public List<LivestreamRespVO> getListLivestream() {
        return CollUtils.convertList(liveStreamRepository.findAll(), LivestreamRespVO::new);
    }

    @Override
    public LiveStream getLiveStreamById(Long id) {
        return this.liveStreamRepository.findById(id)
                .orElseThrow(() -> exception(LIVE_STREAM_NOT_FOUND));
    }
}
