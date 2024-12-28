package com.example.ecommerce.realtime.service.chat;

import com.example.ecommerce.realtime.controller.app.chat.vo.MessageCreateReqVO;
import com.example.ecommerce.realtime.dal.dataobject.chat.Message;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

public interface MessageService {
    Message createMessage(MessageCreateReqVO req);

    List<Message> getListMessageBetweenTwoUsers(Long fromUserId, Long toUserId);

    Message getMessageById(Long id);

    int getTotalUnreadMessageFromUserId(Long fromUserId);

    @Async
    void updateMessageStatusFromReadMessages(Long fromUserId, Long toUserId);

    void revokeMessage(Long msgId);

}
