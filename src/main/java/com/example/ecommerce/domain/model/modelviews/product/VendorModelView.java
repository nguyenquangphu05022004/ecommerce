package com.example.ecommerce.domain.model.modelviews.product;

import com.example.ecommerce.common.utils.SystemUtils;
import com.example.ecommerce.domain.entities.auth.Vendor;
import com.example.ecommerce.domain.model.modelviews.profile.VendorUserProfileModelView;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VendorModelView extends VendorUserProfileModelView {
    private String createdAt;
    private int numberOfFollowers;
    private int numberOfProducts;
    private double percentRelyComment;
    public VendorModelView(Vendor v) {
        super(v);
        this.createdAt = v.getCreatedDate() != null ? SystemUtils.getFormatDate(v.getCreatedDate(), "dd/MM/yyyy") : null;
        this.numberOfFollowers = v.getUsersFavorite() != null ? v.getUsersFavorite().size() : 0;
        this.numberOfProducts = v.getUsersFavorite() != null ? v.getProducts().size() : 0;
        this.percentRelyComment = 5;
    }
}
