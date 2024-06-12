package com.example.ecommerce.domain.dto.user;

import com.example.ecommerce.domain.dto.ENUM.Role;
import com.example.ecommerce.domain.UserContactDetails;
import com.example.ecommerce.domain.Verify;
import com.example.ecommerce.domain.dto.BaseDto;
import com.example.ecommerce.domain.dto.product.BasketDto;
import com.example.ecommerce.domain.dto.product.EvaluationDto;
import com.example.ecommerce.domain.dto.product.ImageDto;
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

public class UserDto extends BaseDto {
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
