package com.example.ecommerce.realtime.service.chat;

import com.example.ecommerce.realtime.controller.app.chat.vo.user.ChatUserRespVO;

import java.util.List;

public interface ChatUserService {

    List<ChatUserRespVO> getListChat(Long userId);
    void deleteChatUser(Long chatUserId);
}
