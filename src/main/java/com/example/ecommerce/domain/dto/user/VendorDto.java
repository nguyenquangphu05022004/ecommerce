package com.example.ecommerce.domain.dto.user;

import com.example.ecommerce.domain.dto.product.ProductDto;
import com.example.ecommerce.domain.dto.BaseDto;
import com.example.ecommerce.domain.dto.product.CouponDto;
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

public class VendorDto extends BaseDto {
    private String shopName;
    private UserResponseInfo user;
    private Integer perMoneyDelivery;

    public String getFormatMoneyDelivery() {
        return SystemUtils.getFormatNumber(perMoneyDelivery);
    }
}
