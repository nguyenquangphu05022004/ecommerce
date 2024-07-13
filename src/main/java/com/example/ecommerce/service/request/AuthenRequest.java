package com.example.ecommerce.service.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenRequest {
    private String username;
    private String password;
}
