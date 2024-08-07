package com.example.ecommerce.repository;

import com.example.ecommerce.domain.entities.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EvaluationImageRepository extends JpaRepository<Evaluation.EvaluationImage, Long> {
    Optional<Evaluation.EvaluationImage> findByName(String name);

    List<Evaluation.EvaluationImage> findAllByEvaluationId(Long id);
}
