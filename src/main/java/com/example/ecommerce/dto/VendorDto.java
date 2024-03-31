package com.example.ecommerce.dto;

import com.example.ecommerce.entity.User;
import com.example.ecommerce.utils.SystemUtils;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)

public class VendorDto extends BaseDto{
    private String shopName;
    private UserDto user;
    private List<ProductDto> products = new ArrayList<>();
    private Integer perMoneyDelivery;

    public String getFormatMoneyDelivery() {
        return SystemUtils.getFormatNumber(perMoneyDelivery);
    }
}
