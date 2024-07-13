package com.example.ecommerce.service.dto;

import com.example.ecommerce.common.utils.SystemUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder(toBuilder = true)
@Getter
@NoArgsConstructor
@Setter
public class VendorDto extends BaseDto {
    private String shopName;
    private Integer perMoneyDelivery;
    private int numberOfProduct;
    private int numberOfUserFavorite;
}
