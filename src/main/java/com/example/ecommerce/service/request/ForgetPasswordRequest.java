package com.example.ecommerce.service.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ForgetPasswordRequest {
    private String password;
    private String code;
}
