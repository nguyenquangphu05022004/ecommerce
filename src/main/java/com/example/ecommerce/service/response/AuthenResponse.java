package com.example.ecommerce.service.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthenResponse {
    private String token;
    private String refreshToken;
    private long expiredAt;
    private String fullName;
}
