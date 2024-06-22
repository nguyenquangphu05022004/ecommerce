package com.example.ecommerce.domain.dto;

import com.example.ecommerce.utils.SystemUtils;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)

public class VendorDto extends BaseDto {
    private String shopName;
    private UserResponseInfo user;
    private Integer perMoneyDelivery;

    public String getFormatMoneyDelivery() {
        return SystemUtils.getFormatNumber(perMoneyDelivery);
    }
}
