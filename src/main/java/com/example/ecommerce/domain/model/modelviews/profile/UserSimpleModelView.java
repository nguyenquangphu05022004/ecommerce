package com.example.ecommerce.domain.model.modelviews.profile;

import com.example.ecommerce.domain.entities.BaseEntity;
import com.example.ecommerce.domain.entities.EntityType;
import com.example.ecommerce.domain.entities.User;
import com.example.ecommerce.service.ImageMapper;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserSimpleModelView extends BaseEntity implements ImageMapper {
    private List<String> urlsImage;
    private String fullName;
    private EntityType userType;
    public UserSimpleModelView(User user) {
        setId(user.getId());
        this.userType = user.getEntityType();
        this.fullName = user.getFullName();
        this.urlsImage = getImageUrl(user.getImages());
    }
}
