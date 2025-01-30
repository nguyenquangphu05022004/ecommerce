package com.example.ecommerce.realtime.service.live;

import com.example.ecommerce.realtime.controller.app.chat.vo.MessageRespVO;
import com.example.ecommerce.realtime.controller.app.live.comment.vo.LiveCommentCreateReqVO;
import com.example.ecommerce.realtime.controller.app.live.comment.vo.LiveCommentRespVO;
import com.example.ecommerce.realtime.dal.dataobject.live.LiveComment;
import com.example.ecommerce.realtime.dal.dataobject.live.LiveStream;
import com.example.ecommerce.realtime.dal.repo.live.LiveCommentRepository;
import com.example.ecommerce.system.service.user.UserMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static com.example.ecommerce.frame.common.exception.utils.ServiceExceptionUtils.exception;
import static com.example.ecommerce.realtime.constants.ErrorCodeConstants.LIVE_COMMENT_NOT_FOUND;

@Service
@Validated
@RequiredArgsConstructor
public class LiveCommentServiceImpl implements LiveCommentService{
    private final LiveCommentRepository liveCommentRepository;
    private final UserMemberService userMemberService;
    private final SimpMessagingTemplate simpMessagingTemplate;
    @Override
    public LiveComment createComment(LiveCommentCreateReqVO reqVO) {

        LiveComment liveComment = LiveComment.builder()
                .content(reqVO.getContent()).likeComment(0)
                .liveStream(LiveStream.builder().id(reqVO.getLivestreamId()).build())
                .userMember(this.userMemberService.getUserMemberById(reqVO.getUserId()))
                .isPinned(reqVO.getPin())
                .build();
        this.liveCommentRepository.save(liveComment);

        simpMessagingTemplate.convertAndSend(
                "/topic/livestream/" + reqVO.getLivestreamId(),
                new LiveCommentRespVO(liveComment));

        return liveComment;
    }

    @Override
    public LiveComment updateComment(Long commentId, String content) {
        LiveComment liveComment = getLiveCommentById(commentId).toBuilder()
                .content(content).build();
        this.liveCommentRepository.save(liveComment);
        return liveComment;
    }

    @Override
    public List<LiveComment> getListCommentByLiveStream(Long liveStreamId) {
        return this.liveCommentRepository.findAllByLiveStreamId(liveStreamId);
    }

    @Override
    public void deleteMessage(Long id) {
        liveCommentRepository.deleteById(id);
    }

    @Override
    public int likeMessage(Long id, Boolean inc) {
        LiveComment liveComment = getLiveCommentById(id);

        liveComment = liveComment.toBuilder()
                .likeComment(inc ? liveComment.getLikeComment() + 1 : liveComment.getLikeComment() - 1)
                .build();
        this.liveCommentRepository.save(liveComment);

        return liveComment.getLikeComment();
    }

    @Override
    public LiveComment getLiveCommentById(Long id) {
        return this.liveCommentRepository.findById(id)
                .orElseThrow(() -> exception(LIVE_COMMENT_NOT_FOUND));
    }

    @Override
    public void pinComment(Long commentId, Boolean isPin) {
        this.liveCommentRepository.updateIsPinned(commentId, isPin);
    }
}
