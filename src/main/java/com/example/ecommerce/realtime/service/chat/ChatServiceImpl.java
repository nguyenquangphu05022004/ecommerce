package com.example.ecommerce.realtime.service.chat;

import com.example.ecommerce.frame.common.collection.CollUtils;
import com.example.ecommerce.frame.common.pojo.Pair;
import com.example.ecommerce.realtime.controller.chat.vo.ConversationRespVO;
import com.example.ecommerce.realtime.controller.chat.vo.ConversationSimpleRespVO;
import com.example.ecommerce.realtime.controller.chat.vo.MessageSimpleRespVO;
import com.example.ecommerce.realtime.dal.dataobject.chat.Message;
import com.example.ecommerce.realtime.dal.repo.chat.MessageRepository;
import com.example.ecommerce.system.service.user.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.example.ecommerce.frame.common.collection.CollUtils.convertList;
import static com.example.ecommerce.frame.common.collection.MapUtils.convertToMap;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService{
    private final MessageRepository messageRepository;
    private final SellerService sellerService;
    @Override
    public List<ConversationSimpleRespVO> searchConversation(String sellerName) {
        return List.of();
    }

    @Override
    public List<ConversationRespVO> getAllConversationFromUserId(Long userId) {
        //this.messageRepository.findLatestMessageOfEachConversationFromUserId(userId
        List<Message> messages = Collections.emptyList();
        List<Object[]> unreadMessage = this.messageRepository.countUnreadMessageFromUserId(userId);

        Map<Long, Message> idMapMessage =
                convertToMap(convertList(messages, msg -> new Pair<>(msg.getId(), msg)));

        return CollUtils.convertList(unreadMessage, objects -> {
            ConversationRespVO conversation = new ConversationRespVO();
            conversation.setChat(true); conversation.setUserId((Long) objects[0]);
            conversation.setNumberUnreadMessage((Integer) objects[1]);
            conversation.setThumbnailAvatar(null); conversation.setDisplayName(null);
            conversation.setMessage(new MessageSimpleRespVO(idMapMessage.get(objects[0])));
            return conversation;
        });
    }
}
