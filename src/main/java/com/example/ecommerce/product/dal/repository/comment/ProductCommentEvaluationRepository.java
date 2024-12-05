package com.example.ecommerce.product.dal.repository.comment;

import com.example.ecommerce.product.dal.dataobject.comment.ProductCommentEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

public interface ProductCommentEvaluationRepository extends JpaRepository<ProductCommentEvaluation, Long> {
   @Modifying
    void deleteAllByProductCommentId(Long commentId);

    Optional<ProductCommentEvaluation> findByProductCommentIdAndProductPropertyId(Long productCommentId, Long productPropertyId);
}
