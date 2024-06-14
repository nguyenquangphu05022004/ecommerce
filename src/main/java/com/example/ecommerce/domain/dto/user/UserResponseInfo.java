package com.example.ecommerce.domain.dto.user;

import com.example.ecommerce.domain.dto.ENUM.Role;
import com.example.ecommerce.domain.UserContactDetails;
import com.example.ecommerce.domain.dto.BaseDto;
import com.example.ecommerce.domain.dto.product.EvaluationDto;
import com.example.ecommerce.domain.dto.product.ImageDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;
@Setter
@Getter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class UserResponseInfo extends BaseDto {
    private String username;
    private String email;
    private ImageDto avatar;
    private Role role;
    private UserContactDetails userContactDetails;
    private List<EvaluationDto> evaluations = new ArrayList<>();
}
