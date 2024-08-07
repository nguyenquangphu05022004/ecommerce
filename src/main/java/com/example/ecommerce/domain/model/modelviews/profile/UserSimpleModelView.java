package com.example.ecommerce.domain.model.modelviews.profile;

import com.example.ecommerce.domain.entities.BaseEntity;
import com.example.ecommerce.domain.entities.auth.User;
import com.example.ecommerce.domain.entities.file.FileEntityType;
import com.example.ecommerce.service.mapper.ImageMapper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserSimpleModelView extends BaseEntity implements ImageMapper {
    private String userImage;
    private String fullName;
    private String userType;
    public UserSimpleModelView(User user) {
        setId(user.getId());
        this.userType = user.getUserType().name();
        this.fullName = user.getFullName();
        this.userImage = getImageUrl(FileEntityType.USER.name(), user.getUserImage());
    }
}
