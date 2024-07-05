package com.example.ecommerce.domain.dto;

import com.example.ecommerce.common.utils.SystemUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@SuperBuilder(toBuilder = true)
@Setter
public class BasketDto extends BaseDto {
    private Integer quantity;
    private Integer totalPrice;
    private StockResponse stock;
    private StockClassificationResponse stockClassification;
    public String getFormatTotalPrice() {
        return SystemUtils.getFormatNumber(this.totalPrice);
    }
}
