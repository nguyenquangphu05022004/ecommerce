package com.example.ecommerce.system.controller.user.vo;

import com.example.ecommerce.system.dal.dataobject.user.Seller;
import lombok.Data;

@Data
public class SellerResVO {
    private String shopName;
    private String shopImage;

    public SellerResVO(Seller seller) {
        this.shopName = seller.getShopName();
        this.shopImage = seller.getShopImage();
    }
}
