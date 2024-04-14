package com.example.ecommerce.repository;

import com.example.ecommerce.entity.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {
    Optional<Evaluation> findByUserUsernameAndProductId(String username, Long productId);
}
