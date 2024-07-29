package com.example.ecommerce.service.dto;

import com.example.ecommerce.domain.entities.auth.Role;
import com.example.ecommerce.domain.UserContactDetails;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    private String username;
    private String email;
    private String image;
    private Role role;
    private UserContactDetails userContactDetails;
    private Integer numEvaluations;
}
