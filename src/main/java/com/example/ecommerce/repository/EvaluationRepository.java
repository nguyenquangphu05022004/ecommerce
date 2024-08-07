package com.example.ecommerce.repository;

import com.example.ecommerce.domain.entities.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {
    Long countByProductId(Long productId);
}
