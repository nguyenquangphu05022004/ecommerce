package com.example.ecommerce.system.dal.repository.auth;

import com.example.ecommerce.system.dal.dataobject.auth.AccessToken;
import com.example.ecommerce.system.dal.dataobject.auth.RefreshToken;
import jakarta.persistence.Access;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccessTokenRepository extends JpaRepository<AccessToken, Long> {
    Optional<AccessToken> findByAccessToken(String accessToken);

    List<AccessToken> findAllByRefreshToken(String refreshToken);
}
