package com.example.ecommerce.system.service.authen;

import com.example.ecommerce.system.controller.admin.auth.vo.AuthLoginReqVO;
import com.example.ecommerce.system.controller.admin.auth.vo.AuthLoginResVO;

public interface AuthService {
    AuthLoginResVO login(AuthLoginReqVO reqVO);
    void logout(String accessToken);
    AuthLoginResVO refreshToken(String refreshToken);

}
