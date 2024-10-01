package com.example.ecommerce.domain.model.modelviews.messages;

import com.example.ecommerce.domain.entities.BaseEntity;
import com.example.ecommerce.domain.entities.EntityType;
import com.example.ecommerce.domain.entities.Message;
import com.example.ecommerce.domain.model.modelviews.profile.UserSimpleModelView;
import com.example.ecommerce.service.ImageMapper;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class MessageModelView extends BaseEntity implements ImageMapper {
    private String content;
    private UserSimpleModelView fromUser;
    private boolean watched;
    private List<String> urlsImage;
    private Destination destination;
    public MessageModelView(Message message) {
        setId(message.getId());
        this.content = message.getContent();
        fromUser = new UserSimpleModelView(message.getFromUser());
        this.watched = message.isWatched();
        urlsImage = getImageUrl(message.getImages());
        this.destination = new Destination(message.getToEntityType(), null, null);
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class Destination {
        private EntityType entityType;
        private String name;
        private String urlImage;

    }
}
