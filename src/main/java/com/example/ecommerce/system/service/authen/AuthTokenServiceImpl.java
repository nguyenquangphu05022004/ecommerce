package com.example.ecommerce.system.service.authen;

import com.example.ecommerce.system.dal.dataobject.auth.AccessToken;
import com.example.ecommerce.system.dal.redis.dao.RedisAuthTokenDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthTokenServiceImpl implements AuthTokenService{

    private final RedisAuthTokenDao authDao;
    @Override
    public AccessToken createAccessToken(Long userId) {
        return null;
    }

    @Override
    public AccessToken getByAccessToken(String accessToken) {
    }

    @Override
    public void deleteByAccessToken(String accessToken) {

    }

    @Override
    public AccessToken refreshAccessToken(String freshToken) {
        return null;
    }
}
