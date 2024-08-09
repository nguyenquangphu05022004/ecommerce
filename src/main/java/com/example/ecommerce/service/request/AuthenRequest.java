package com.example.ecommerce.service.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthenRequest {
    @NotEmpty
    @Length(min = 6)
    private String username;
    @NotEmpty
    @Length(min = 6)
    private String password;
}
