package com.example.ecommerce.domain.response;

import com.example.ecommerce.domain.entities.EntityType;
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
    private EntityType entityType;
}
