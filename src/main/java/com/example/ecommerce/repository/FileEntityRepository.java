package com.example.ecommerce.repository;

import com.example.ecommerce.domain.entities.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileEntityRepository extends JpaRepository<FileEntity, Long> {
}
