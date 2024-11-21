package com.example.ecommerce.system.dal.redis.dao;

import com.example.ecommerce.system.dal.dataobject.auth.AccessToken;
import org.springframework.stereotype.Component;

@Component
public class RedisAuthTokenDao {


    public AccessToken getAccessToken(String accessToken) {
        return null;
    }

    public void setAccessToken(AccessToken accessToken) {

    }
}
