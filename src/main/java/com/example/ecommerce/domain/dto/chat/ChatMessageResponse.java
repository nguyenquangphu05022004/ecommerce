package com.example.ecommerce.domain.dto.chat;

import com.example.ecommerce.domain.dto.BaseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
public class ChatMessageResponse extends BaseDto {
    private String content;
    private UserSender userSender;
    private ConversationResponse conversationResponse;
    private MessageType messageType;
    private String updatedAt;
    private List<String> urlMedia;
    public static enum MessageType {
        RECEIVE,
        SEND
    }
    @Getter
    @Builder
    public static class UserSender{
        private String fullName;
        private String username;
        private String image;
    }
}
