package com.example.ecommerce.repository;

import com.example.ecommerce.domain.FileEntity;
import com.example.ecommerce.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileEntityRepository extends JpaRepository<FileEntity, Long> {

}
