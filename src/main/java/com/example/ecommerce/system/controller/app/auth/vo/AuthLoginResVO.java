package com.example.ecommerce.system.controller.app.auth.vo;

import com.example.ecommerce.system.dal.dataobject.auth.AccessToken;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AuthLoginResVO {
    private Long userId;
    private String fullName;
    private String accessToken;
    private String refreshToken;
    private LocalDateTime expires;


    public AuthLoginResVO(AccessToken accessToken) {
        this.userId = accessToken.getUserMember().getId();
        this.fullName = accessToken.getUserMember().getFullName();
        this.accessToken = accessToken.getAccessToken();
        this.refreshToken = accessToken.getRefreshToken();
        this.expires = accessToken.getExpires();
    }
}
