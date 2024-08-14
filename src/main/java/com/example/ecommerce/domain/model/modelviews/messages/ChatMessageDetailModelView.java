package com.example.ecommerce.domain.model.modelviews.messages;

import com.example.ecommerce.domain.entities.chat.ChatMessage;
import com.example.ecommerce.domain.model.modelviews.profile.UserSimpleModelView;
import com.example.ecommerce.service.ImageMapper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class ChatMessageDetailModelView extends ChatMessageGalleryModelView implements ImageMapper {
    private UserSimpleModelView toUser;
    public ChatMessageDetailModelView(ChatMessage chatMessage) {
        super(chatMessage);
        this.toUser = new UserSimpleModelView(chatMessage.getToUser());
    }
}
