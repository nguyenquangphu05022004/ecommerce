package com.example.ecommerce.repository;

import com.example.ecommerce.entity.Role;
import com.example.ecommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    List<User> findAllByRole(Role role);
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
    Optional<User> findByVerifyCodeAndVerifyStatus(String code, Boolean status);
}
