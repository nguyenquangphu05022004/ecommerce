package com.example.ecommerce.domain.model.dto;

import com.example.ecommerce.domain.entities.BaseEntity;
import com.example.ecommerce.domain.entities.auth.Role;
import com.example.ecommerce.domain.entities.auth.UserType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto extends BaseEntity {
    private String fullName;
    private String username; //email
    private UserType userType;
    private Long userTypeId;
    private Role role;
    private boolean isOnline;
}
