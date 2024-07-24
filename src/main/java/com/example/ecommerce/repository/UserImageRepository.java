package com.example.ecommerce.repository;

import com.example.ecommerce.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserImageRepository extends JpaRepository<User.UserImage, Long> {
    Optional<User.UserImage> findByName(String name);
}
