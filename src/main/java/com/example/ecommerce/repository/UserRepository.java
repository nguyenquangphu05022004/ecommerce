package com.example.ecommerce.repository;

import com.example.ecommerce.domain.entities.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsernameIgnoreCase(String username);
   @Modifying
    void deleteByUsername(String username);
}
