package com.example.ecommerce.repository;

import com.example.ecommerce.common.enums.TokenType;
import com.example.ecommerce.domain.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByValue(String value);
    List<Token> findAllByUserId(Long userId);
    Optional<Token> findByUserIdAndTokenType(Long userId, TokenType tokenType);
}
