package com.example.ecommerce.domain.dto;

import com.example.ecommerce.common.utils.SystemUtils;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class VendorDto extends BaseDto {
    private String shopName;
    private Integer perMoneyDelivery;
    private int numberOfProduct;
    private int numberOfUserFavorite;
    public String getFormatMoneyDelivery() {
        return SystemUtils.getFormatNumber(perMoneyDelivery);
    }

}
