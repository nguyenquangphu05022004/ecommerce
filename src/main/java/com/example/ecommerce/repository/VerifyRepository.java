package com.example.ecommerce.repository;

import com.example.ecommerce.domain.Verify;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VerifyRepository extends JpaRepository<Verify, Long> {
    Optional<Verify> findByCode(String code);
}
