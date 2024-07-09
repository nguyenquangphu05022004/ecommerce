package com.example.ecommerce.service.dto;

import com.example.ecommerce.common.utils.SystemUtils;
import com.example.ecommerce.domain.dto.BaseDto;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder(toBuilder = true)
@Getter
public class VendorDto extends BaseDto {
    private String shopName;
    private Integer perMoneyDelivery;
    private int numberOfProduct;
    private int numberOfUserFavorite;
    public String getFormatMoneyDelivery() {
        return SystemUtils.getFormatNumber(perMoneyDelivery);
    }
}
