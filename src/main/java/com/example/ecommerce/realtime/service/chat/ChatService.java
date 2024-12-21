package com.example.ecommerce.realtime.service.chat;

import com.example.ecommerce.realtime.controller.chat.vo.ConversationRespVO;
import com.example.ecommerce.realtime.controller.chat.vo.ConversationSimpleRespVO;

import java.util.List;

public interface ChatService {
    List<ConversationSimpleRespVO> searchConversation(String sellerName);

    List<ConversationRespVO> getAllConversationFromUserId(Long userId);
}
