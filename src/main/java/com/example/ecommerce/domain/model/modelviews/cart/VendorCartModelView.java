package com.example.ecommerce.domain.model.modelviews.cart;


import com.example.ecommerce.domain.entities.auth.Vendor;
import com.example.ecommerce.domain.model.modelviews.profile.VendorModelView;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@NoArgsConstructor
@Setter
public class VendorCartModelView extends VendorModelView {
    private List<ItemCartModelView> items;
    public VendorCartModelView(Vendor v) {
        super(v);
    }

}
