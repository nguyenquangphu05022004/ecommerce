package com.example.ecommerce.system.dal.repository.auth;

import com.example.ecommerce.system.dal.dataobject.auth.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByRefreshToken(String refreshToken);

    @Modifying
    void deleteByRefreshToken(String refreshToken);
}
