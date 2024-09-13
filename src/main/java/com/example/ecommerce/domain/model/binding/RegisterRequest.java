package com.example.ecommerce.domain.model.binding;

import com.example.ecommerce.common.InvalidMessage;
import com.example.ecommerce.domain.entities.auth.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegisterRequest {
    @NotEmpty(message = InvalidMessage.NOT_EMPTY)
    @Length(min = 10)
    private String username;
    @NotEmpty(message = InvalidMessage.NOT_EMPTY)
    @Length(min = 8)
    private String password;
    @NotEmpty(message = InvalidMessage.NOT_EMPTY)
    @Length(min = 10)
    private String fullName;
    private Role role;
    @NotNull(message = InvalidMessage.NOT_NULL)
    private LocalDateTime dateOfBirth;
}
