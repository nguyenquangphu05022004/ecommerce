package com.example.ecommerce.product.dal.repository.comment;

import com.example.ecommerce.product.dal.dataobject.comment.ProductCommentFavorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

public interface ProductCommentFavoriteRepository extends JpaRepository<ProductCommentFavorite, Long> {
    Optional<ProductCommentFavorite> findByProductCommentIdAndUserMemberId(Long productCommentId, Long userMemberId);
    int countAllByProductCommentId(Long productCommentId);

    @Modifying
    void deleteByUserMemberIdAndProductCommentId(Long userMemberId, Long productCommentId);

}
