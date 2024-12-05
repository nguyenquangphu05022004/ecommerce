package com.example.ecommerce.system.dal.dataobject.user;

import com.example.ecommerce.frame.auditting.BaseEntity;
import com.example.ecommerce.product.dal.dataobject.spu.ProductSpu;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;


@Entity
@Table(name = "user_seller")
@NoArgsConstructor
@SuperBuilder
@Setter
public class Seller extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "user_member_id", unique = true)
    private UserMember userMember;

    private String shopName;

    /**
     * List product is sold by seller;
     */
    @OneToMany(mappedBy = "seller")
    private List<ProductSpu> productSpus;

}
