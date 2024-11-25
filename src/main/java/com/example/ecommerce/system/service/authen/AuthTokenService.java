package com.example.ecommerce.system.service.authen;

import com.example.ecommerce.system.dal.dataobject.auth.AccessToken;

public interface AuthTokenService {

    AccessToken createAccessToken(Long userId);
    void deleteByAccessToken(String accessToken);
    AccessToken refreshAccessToken(String freshToken);
    AccessToken getAccessToken(String accessToken);
}
