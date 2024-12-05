package com.example.ecommerce.product.dal.repository.comment;

import com.example.ecommerce.product.dal.dataobject.comment.ProductEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductEvaluationRepository extends JpaRepository<ProductEvaluation, Long> {
    List<ProductEvaluation> findAllByProductSpuId(Long productSpuId);
}
