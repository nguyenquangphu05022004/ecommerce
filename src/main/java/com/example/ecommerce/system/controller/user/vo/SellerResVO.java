package com.example.ecommerce.system.controller.user.vo;

import com.example.ecommerce.system.dal.dataobject.user.Seller;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Objects;

@Data
@NoArgsConstructor
public class SellerResVO {
    private String shopName;
    private String shopImage;
    private Long id;
    public SellerResVO(Seller seller) {
        this.shopName = seller.getShopName();
        this.shopImage = seller.getShopImage();
        this.id = seller.getId();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof SellerResVO that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
