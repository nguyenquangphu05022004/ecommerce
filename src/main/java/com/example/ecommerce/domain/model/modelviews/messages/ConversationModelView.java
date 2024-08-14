package com.example.ecommerce.domain.model.modelviews.messages;

import com.example.ecommerce.domain.entities.chat.Conversation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ConversationModelView {
    private Long id;
    private String name;
    public ConversationModelView(Conversation conversation) {
        this.id = conversation.getId();
        this.name = conversation.getName();
    }
}
