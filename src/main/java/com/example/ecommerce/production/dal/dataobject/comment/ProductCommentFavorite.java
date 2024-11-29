package com.example.ecommerce.production.dal.dataobject.comment;

import com.example.ecommerce.frame.auditting.BaseEntity;
import com.example.ecommerce.system.dal.dataobject.user.UserMember;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "production_product_comment_favorite")
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Getter
public class ProductCommentFavorite extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_member_id")
    private UserMember userMember;

    @ManyToOne
    @JoinColumn(name = "product_comment_id")
    private ProductComment productComment;


    public ProductCommentFavorite(Long userMember, Long productCommentId) {
        this.userMember = UserMember.builder().id(userMember).build();
        this.productComment = ProductComment.builder().id(productCommentId).build();
    }
}
