package com.example.ecommerce.product.dal.dataobject.comment;

import com.example.ecommerce.frame.auditting.BaseEntity;
import com.example.ecommerce.frame.common.converter.JsonListConverter;
import com.example.ecommerce.product.dal.dataobject.sku.ProductSku;
import com.example.ecommerce.product.dal.dataobject.spu.ProductSpu;
import com.example.ecommerce.system.dal.dataobject.user.UserMember;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Table(name = "production_product_comment")
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Getter
@Setter
public class ProductComment extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_member_id")
    private UserMember userMember;

//    private UserMemberType userMemberType;

    @Convert(converter = JsonListConverter.class)
    private List<String> imageUrls;

    @ManyToOne
    @JoinColumn(name = "product_spu_id")
    private ProductSpu productSpu;

    @ManyToOne
    @JoinColumn(name = "product_sku_id")
    private ProductSku productSku;

    @ManyToOne
    @JoinColumn(name = "reply_product_comment")
    private ProductComment replyProductComment;
    @OneToMany(mappedBy = "replyProductComment")
    private List<ProductComment> productCommentChildren;

    @OneToMany(mappedBy = "productComment")
    private List<ProductCommentEvaluation> productCommentEvaluations;

    @OneToMany(mappedBy = "productComment")
    private List<ProductCommentFavorite> productCommentFavorites;
    private String content;
    private Double rating;

}
