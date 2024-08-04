package com.example.ecommerce.service.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenResponse {
    private String token;
    private String refreshToken;
    private long expiredAt;
    private String fullName;
}
