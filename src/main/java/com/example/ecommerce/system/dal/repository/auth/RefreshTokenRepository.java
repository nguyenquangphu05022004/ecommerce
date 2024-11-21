package com.example.ecommerce.system.dal.repository.auth;

import com.example.ecommerce.system.dal.dataobject.auth.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
}
