package com.example.ecommerce.domain.dto;

import com.example.ecommerce.domain.UserContactDetails;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
    private String username;
    private String password;
    private String email;
    private UserContactDetails userContactDetails;
}
