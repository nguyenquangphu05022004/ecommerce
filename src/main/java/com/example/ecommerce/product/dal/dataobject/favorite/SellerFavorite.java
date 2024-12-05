package com.example.ecommerce.product.dal.dataobject.favorite;

import com.example.ecommerce.frame.auditting.BaseEntity;
import com.example.ecommerce.system.dal.dataobject.user.Seller;
import com.example.ecommerce.system.dal.dataobject.user.UserMember;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder(toBuilder = true)
@Entity
@Table(name = "production_seller_favorite")
@NoArgsConstructor
@Setter
public class SellerFavorite extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "user_member_id")
    private UserMember userMember;
    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;
}
