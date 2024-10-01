package com.example.ecommerce.domain.model.modelviews.profile;

import com.example.ecommerce.domain.entities.BaseEntity;
import com.example.ecommerce.domain.entities.Vendor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class VendorUserProfileModelView extends BaseEntity {
    private String shopName;

    public VendorUserProfileModelView(Vendor v) {
        super(v.getId(), v.getCreatedBy(), v.getModifiedBy(), v.getCreatedDate(), v.getModifiedDate());
        this.shopName = v.getShopName();
    }
}
