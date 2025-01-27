package com.example.ecommerce.realtime.service.live;

import com.example.ecommerce.realtime.controller.app.live.comment.vo.LiveCommentCreateReqVO;
import com.example.ecommerce.realtime.dal.dataobject.live.LiveComment;

import java.util.List;

public interface LiveCommentService {
    LiveComment createComment(LiveCommentCreateReqVO reqVO);
    LiveComment updateComment(Long commentId, String content);
    List<LiveComment> getListCommentByLiveStream(Long liveStreamId);
    void deleteMessage(Long id);

    int likeMessage(Long id, Boolean inc);

    LiveComment getLiveCommentById(Long id);

    void pinComment(Long commentId, Boolean isPin);
}
