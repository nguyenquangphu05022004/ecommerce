package com.example.ecommerce.domain.model.modelviews.profile;

import com.example.ecommerce.domain.entities.auth.Vendor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class VendorUserProfileModelView {
    private Long id;
    private String shopName;

    public VendorUserProfileModelView(Vendor v) {
        if(v != null) {
            this.id = v.getId();
            this.shopName = v.getShopName();
        }
    }
}
