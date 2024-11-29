package com.example.ecommerce.production.dal.repository.comment;

import com.example.ecommerce.production.dal.dataobject.comment.ProductCommentEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

public interface ProductCommentEvaluationRepository extends JpaRepository<ProductCommentEvaluation, Long> {
   @Modifying
    void deleteAllByProductCommentId(Long commentId);
}
