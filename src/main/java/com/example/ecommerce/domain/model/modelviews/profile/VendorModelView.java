package com.example.ecommerce.domain.model.modelviews.profile;

import com.example.ecommerce.domain.entities.auth.Vendor;
import lombok.Getter;

@Getter
public class VendorModelView {
    private Long id;
    private String shopName;

    public VendorModelView(Vendor v) {
        this.id = v.getId();
        this.shopName = v.getShopName();
    }
}
