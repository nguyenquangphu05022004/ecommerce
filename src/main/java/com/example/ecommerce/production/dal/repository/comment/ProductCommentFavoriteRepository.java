package com.example.ecommerce.production.dal.repository.comment;

import com.example.ecommerce.production.dal.dataobject.comment.ProductCommentFavorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductCommentFavoriteRepository extends JpaRepository<ProductCommentFavorite, Long> {
    Optional<ProductCommentFavorite> findByProductCommentIdAndUserMemberId(Long productCommentId, Long userMemberId);
    int countAllByProductCommentId(Long productCommentId);
}
