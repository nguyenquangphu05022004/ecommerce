package com.example.ecommerce.dto;

import com.example.ecommerce.entity.User;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VendorDto extends BaseDto{
    private String shopName;
    private UserDto user;
    private List<ProductDto> products = new ArrayList<>();
    private Integer perMoneyDelivery;
}
