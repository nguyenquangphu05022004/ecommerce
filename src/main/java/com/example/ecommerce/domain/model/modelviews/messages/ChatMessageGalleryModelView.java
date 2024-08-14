package com.example.ecommerce.domain.model.modelviews.messages;

import com.example.ecommerce.domain.entities.BaseEntity;
import com.example.ecommerce.domain.entities.chat.ChatMessageEntity;
import com.example.ecommerce.domain.model.modelviews.profile.UserSimpleModelView;
import com.example.ecommerce.service.ImageMapper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static com.example.ecommerce.domain.entities.file.FileEntityType.CHAT_MESSAGE;
@Getter
@Setter
@NoArgsConstructor
public class ChatMessageGalleryModelView extends BaseEntity implements ImageMapper {
    private String content;
    private ChatMessageType chatMessageType;
    public List<String> urlImages;
    private UserSimpleModelView fromUser;

    public ChatMessageGalleryModelView(ChatMessageEntity entity) {
        setId(entity.getId());
        setCreatedDate(entity.getCreatedDate());
        this.content = entity.getContent();
        this.chatMessageType = new ChatMessageType(entity);
        this.fromUser = new UserSimpleModelView(entity.getFromUser());
        this.urlImages = getImageUrl(CHAT_MESSAGE.name(), entity.getChatMessageImages());
    }
}
