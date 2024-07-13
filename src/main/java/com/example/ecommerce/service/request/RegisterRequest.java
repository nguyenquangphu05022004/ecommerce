package com.example.ecommerce.service.request;

import com.example.ecommerce.common.enums.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegisterRequest {
    private String username;
    private String password;
    private String email;
    private String fullName;
    private Role role;
}
