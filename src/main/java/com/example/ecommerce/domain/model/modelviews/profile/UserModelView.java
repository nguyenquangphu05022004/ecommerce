package com.example.ecommerce.domain.model.modelviews.profile;


import com.example.ecommerce.domain.entities.User;
import com.example.ecommerce.domain.model.modelviews.evaluation.EvaluationProfileModelView;
import com.example.ecommerce.domain.model.modelviews.order.OrderModelView;
import com.example.ecommerce.service.ImageMapper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@NoArgsConstructor
@Setter
public class UserModelView extends UserSimpleModelView implements ImageMapper { ;
    private List<EvaluationProfileModelView> evaluations;
    private List<OrderModelView> orders;
    private List<VendorUserProfileModelView> vendors;

    public UserModelView(User user) {
        super(user);
        this.evaluations = user.getEvaluations() != null ? user.getEvaluations().stream().map(e -> new EvaluationProfileModelView(e)).toList() : null;
        this.vendors = user.getVendors() != null ? user.getVendors().stream().map(e -> new VendorUserProfileModelView(e)).toList() : null;
    }
}
