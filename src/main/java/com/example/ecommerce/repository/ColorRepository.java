package com.example.ecommerce.repository;

import com.example.ecommerce.domain.Color;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorRepository extends JpaRepository<Color, Long> {
}
