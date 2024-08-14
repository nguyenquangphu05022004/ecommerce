package com.example.ecommerce.domain.model.modelviews.messages;

import com.example.ecommerce.domain.entities.chat.ChatMessage;
import com.example.ecommerce.domain.entities.chat.ChatMessageEntity;
import com.example.ecommerce.domain.entities.chat.ChatMessageConversation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChatMessageType {
    private Long destinationId;
    private boolean isUser;
    public ChatMessageType(ChatMessageEntity entity) {
        this.isUser = checkChatMessageConversation(entity);
        this.destinationId = checkChatMessageConversation(entity) ?
                ((ChatMessageConversation)entity).getToConversation().getId()
                : ((ChatMessage)entity).getToUser().getId();
    }

    private boolean checkChatMessageConversation(ChatMessageEntity entity) {
        if(entity instanceof ChatMessage) {
            return false;
        }
        return true;
    }
}
