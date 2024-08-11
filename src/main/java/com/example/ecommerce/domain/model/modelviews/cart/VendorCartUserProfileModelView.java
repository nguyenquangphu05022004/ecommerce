package com.example.ecommerce.domain.model.modelviews.cart;


import com.example.ecommerce.domain.entities.auth.Vendor;
import com.example.ecommerce.domain.model.modelviews.profile.VendorUserProfileModelView;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@NoArgsConstructor
@Setter
public class VendorCartUserProfileModelView extends VendorUserProfileModelView {
    private List<ItemCartModelView> items;
    public VendorCartUserProfileModelView(Vendor v) {
        super(v);
    }

}
