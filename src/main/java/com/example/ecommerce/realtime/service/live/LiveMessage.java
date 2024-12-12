package com.example.ecommerce.realtime.service.live;

import com.example.ecommerce.realtime.controller.live.livemessage.vo.LiveMessageCreateReqVO;
import com.example.ecommerce.realtime.controller.live.livemessage.vo.LiveMessagePagingReqVO;
import com.example.ecommerce.realtime.controller.live.livemessage.vo.LiveMessageReplyCreateReqVO;

public interface LiveMessage {
    void createMessage(Long userId, LiveMessageCreateReqVO reqVO);
    void replyMessage(Long userId, LiveMessageReplyCreateReqVO reqVO);
    void getPagingMessage(LiveMessagePagingReqVO reqVO);
    void deleteMessage(Long liveMessageId);
    void likeMessage(Long liveMessageId);
}
