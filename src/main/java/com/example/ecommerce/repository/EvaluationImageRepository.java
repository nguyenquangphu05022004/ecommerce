package com.example.ecommerce.repository;

import com.example.ecommerce.domain.EvaluationImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EvaluationImageRepository extends JpaRepository<EvaluationImage, Long> {
    Optional<EvaluationImage> findByName(String name);

}
