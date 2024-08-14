package com.example.ecommerce.domain.model.modelviews.messages;

import com.example.ecommerce.domain.entities.chat.ChatMessageConversation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChatMessageConversationModelView extends ChatMessageGalleryModelView {
    private ConversationModelView conversation;
    public ChatMessageConversationModelView(ChatMessageConversation group) {
        super(group);
        this.conversation = new ConversationModelView(group.getToConversation());
    }
}
