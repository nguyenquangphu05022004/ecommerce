package com.example.ecommerce.system.dal.dataobject.auth;

import com.example.ecommerce.domain.entities.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AccessToken {
    private User user;
    private String accessToken;
    private String refreshToken;
    private LocalDateTime expires;
}
