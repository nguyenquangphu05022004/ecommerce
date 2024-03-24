package com.example.ecommerce.repository;

import com.example.ecommerce.entity.Verify;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VerifyRepository extends JpaRepository<Verify, Long> {
    Optional<Verify> findByCodeAndStatus(String code, Boolean status);
    boolean existsByCodeAndStatus(String code, Boolean status);
}
