package com.example.ecommerce.dto;

import com.example.ecommerce.entity.Role;
import com.example.ecommerce.entity.UserContactDetails;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
@Data
@Getter
@NoArgsConstructor
public class UserDto extends BaseDto{
    private String username;
    private String password;
    private String email;
    private Role role;
    private UserContactDetails userContactDetails;
    private BasketDto basket;
    private VendorDto vendor;
    private List<EvaluationDto> feedBacks = new ArrayList<>();
}
