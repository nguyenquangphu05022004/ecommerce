package com.example.ecommerce.dto;

import com.example.ecommerce.domain.Role;
import com.example.ecommerce.domain.UserContactDetails;
import com.example.ecommerce.domain.Verify;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;
@Data
@Getter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)

public class UserDto extends BaseDto{
    private String username;
    private String password;
    private String email;
    private ImageDto avatar;
    private Role role;
    private UserContactDetails userContactDetails;
    private BasketDto basket;
    private VendorDto vendor;
    private Verify verify;
    private List<EvaluationDto> evaluations = new ArrayList<>();
}
