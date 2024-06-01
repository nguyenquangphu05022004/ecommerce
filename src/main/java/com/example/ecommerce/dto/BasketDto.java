package com.example.ecommerce.dto;

import com.example.ecommerce.utils.SystemUtils;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@SuperBuilder(toBuilder = true)
@Setter
public class BasketDto extends BaseDto{
    private Integer quantity;
    private Integer totalPrice;
    private StockResponse stockResponse;
    public String getFormatTotalPrice() {
        return SystemUtils.getFormatNumber(this.totalPrice);
    }
}
