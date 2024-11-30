//package com.example.ecommerce.production.dal.dataobject.favorite;
//
//import com.example.ecommerce.frame.auditting.BaseEntity;
//import com.example.ecommerce.production.dal.dataobject.spu.ProductSpu;
//import com.example.ecommerce.system.dal.dataobject.user.UserMember;
//import jakarta.persistence.Entity;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.Table;
//import lombok.NoArgsConstructor;
//import lombok.experimental.SuperBuilder;
//
//@Entity
//@Table(name = "production_product_favorite")
//@NoArgsConstructor
//@SuperBuilder(toBuilder = true)
//public class ProductFavorite extends BaseEntity {
//    @ManyToOne
//    @JoinColumn(name = "user_member_id")
//    private UserMember userMember;
//
//    @ManyToOne
//    @JoinColumn(name = "product_spu_id")
//    private ProductSpu productSpu;
//}
