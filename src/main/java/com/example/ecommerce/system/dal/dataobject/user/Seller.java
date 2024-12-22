package com.example.ecommerce.system.dal.dataobject.user;

import com.example.ecommerce.product.dal.dataobject.spu.ProductSpu;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;


@Entity
@Table(name = "user_seller")
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@DiscriminatorValue("SELLER")
public class Seller extends UserMember {


    private String shopName;
    private String shopImage;

    /**
     * List product is sold by seller;
     */
    @OneToMany(mappedBy = "seller")
    private List<ProductSpu> productSpus;

}
