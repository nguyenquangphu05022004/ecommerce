package com.example.ecommerce.domain.model.modelviews.product;

import com.example.ecommerce.common.utils.SystemUtils;
import com.example.ecommerce.domain.entities.auth.Vendor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VendorModelView extends com.example.ecommerce.domain.model.modelviews.profile.VendorModelView {
    private String createdAt;
    private int numberOfFollowers;
    private int numberOfProducts;
    private double percentRelyComment;
    public VendorModelView(Vendor v) {
        super(v);
        this.createdAt = SystemUtils.getFormatDate(v.getCreatedDate(), "dd/MM/yyyy");
        this.numberOfFollowers =  v.getUsers().size();
        this.numberOfProducts = v.getProducts().size();
        this.percentRelyComment = 5;
    }
}
