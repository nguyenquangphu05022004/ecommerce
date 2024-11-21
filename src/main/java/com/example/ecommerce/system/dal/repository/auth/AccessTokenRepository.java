package com.example.ecommerce.system.dal.repository.auth;

import com.example.ecommerce.system.dal.dataobject.auth.AccessToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessTokenRepository extends JpaRepository<AccessToken, Long> {
}
