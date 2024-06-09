package com.example.ecommerce.domain.response;

import com.example.ecommerce.dto.BaseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class ChatMessageResponse extends BaseDto {
    private String content;
    private UserSender userSender;
    private ConversationResponseMessage conversationResponseMessage;
    private MessageType messageType;
    private String updatedAt;
    public static enum MessageType {
        RECEIVE,
        SEND
    }
    @Getter
    @Builder
    public static class ConversationResponseMessage {
        private Long id;
        private String conversationName;
    }
    @Getter
    @Builder
    public static class UserSender{
        private String fullName;
        private String username;
    }
}
