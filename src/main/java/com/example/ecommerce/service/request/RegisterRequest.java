package com.example.ecommerce.service.request;

import com.example.ecommerce.domain.entities.auth.Role;
import com.example.ecommerce.domain.entities.auth.UserType;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegisterRequest {
    @NotEmpty
    @Length(min = 10)
    private String username;
    @NotEmpty
    @Length(min = 8)
    private String password;
    @NotEmpty
    @Length(min = 10)
    private String fullName;
    private Role role;
    @NotNull
    private UserType userType;
}
