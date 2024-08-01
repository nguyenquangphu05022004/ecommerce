package com.example.ecommerce.domain.model.modelviews.profile;


import com.example.ecommerce.domain.entities.auth.User;
import com.example.ecommerce.domain.entities.file.FileEntityType;
import com.example.ecommerce.domain.model.modelviews.order.OrderViewModel;
import com.example.ecommerce.service.mapper.ImageMapper;
import lombok.Getter;

import java.util.List;

@Getter
public class UserModelView implements ImageMapper {
    private long id;
    private String avatar;
    private List<EvaluationModelView> evaluations;
    private List<OrderViewModel> orders;
    private List<VendorModelView> vendors;

    public UserModelView(User user) {
        this.id = user.getId();
        this.avatar = getImageUrl(FileEntityType.USER.name(), user.getUserImage());
        this.evaluations = user.getEvaluations() != null ? user.getEvaluations().stream().map(e -> new EvaluationModelView(e)).toList() : null;
        this.vendors = user.getVendors() != null ? user.getVendors().stream().map(e -> new VendorModelView(e)).toList() : null;
    }
}
