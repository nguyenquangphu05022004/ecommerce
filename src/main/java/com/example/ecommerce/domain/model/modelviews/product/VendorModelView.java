package com.example.ecommerce.domain.model.modelviews.product;

import com.example.ecommerce.common.SystemUtils;
import com.example.ecommerce.domain.entities.Vendor;
import com.example.ecommerce.domain.model.modelviews.profile.VendorUserProfileModelView;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VendorModelView extends VendorUserProfileModelView {
    private int numberOfFollowers;
    private int numberOfProducts;
    private double percentRelyComment;
    public VendorModelView(Vendor v) {
        super(v);
        this.numberOfFollowers = v.getUsersFavorite() != null ? v.getUsersFavorite().size() : 0;
        this.numberOfProducts = v.getUsersFavorite() != null ? v.getProducts().size() : 0;
        this.percentRelyComment = 5;
    }
}
