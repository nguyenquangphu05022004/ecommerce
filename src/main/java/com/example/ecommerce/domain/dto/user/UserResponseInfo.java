package com.example.ecommerce.domain.dto.user;

import com.example.ecommerce.domain.UserContactDetails;
import com.example.ecommerce.domain.dto.ENUM.Role;
import com.example.ecommerce.domain.dto.product.ImageDto;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class UserResponseInfo {
    private String email;
    private ImageDto image;
    private Role role;
    private UserContactDetails userContactDetails;
    private int numberOfEvaluation;
}
