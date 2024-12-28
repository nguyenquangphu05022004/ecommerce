package com.example.ecommerce.realtime.service.chat;

import com.example.ecommerce.frame.common.collection.CollUtils;
import com.example.ecommerce.frame.common.collection.StreamUtils;
import com.example.ecommerce.realtime.controller.app.chat.vo.MessageCreateReqVO;
import com.example.ecommerce.realtime.dal.dataobject.chat.Message;
import com.example.ecommerce.realtime.dal.repo.chat.MessageRepository;
import com.example.ecommerce.system.service.user.UserMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static com.example.ecommerce.frame.common.exception.utils.ServiceExceptionUtils.exception;
import static com.example.ecommerce.realtime.constants.ErrorCodeConstants.MESSAGE_NOT_FOUND;

@Service
@Validated
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService{

    private final UserMemberService userMemberService;
    private final MessageRepository messageRepository;

    @Override
    public Message createMessage(MessageCreateReqVO req) {
        Message message = Message.builder()
                .readMessage(false).content(req.getContent())
                .fromUser(userMemberService.getUserMemberById(req.getFromUserId()))
                .toUser(userMemberService.getUserMemberById(req.getToUserId()))
                .revokeMessage(false)
                .build();
        try {
            message.setReplyMessage(getMessageById(req.getReplyMessageId()));
        } finally {
            message.setReplyMessage(null);
        }
        this.messageRepository.save(message);
        return message;
    }

    @Override
    public List<Message> getListMessageBetweenTwoUsers(Long fromUserId, Long toUserId) {
        List<Message> messages = this.messageRepository.findAllMessageBetweenTwoUsers(fromUserId, toUserId);
        this.updateMessageStatusFromReadMessages(fromUserId, toUserId);
        return messages;
    }



    @Override
    public Message getMessageById(Long id) {
        return this.messageRepository.findById(id)
                .orElseThrow(() -> exception(MESSAGE_NOT_FOUND));
    }

    @Override
    public int getTotalUnreadMessageFromUserId(Long fromUserId) {
        List<Object[]> objects = this.messageRepository.countUnreadMessageFromUserId(fromUserId);
        if(CollUtils.isEmpty(objects)) return 0;
        return StreamUtils.mapInt(objects, arr -> (int) arr[1]).sum();
    }

    @Override
    public void updateMessageStatusFromReadMessages(Long fromUserId, Long toUserId) {
        this.messageRepository.updateStatusFromReadMessages(fromUserId, toUserId);
    }

    @Override
    public void revokeMessage(Long msgId) {
        Message message = getMessageById(msgId);
        message.setRevokeMessage(true);
        this.messageRepository.save(message);
        //using websocket send to client
    }
}
