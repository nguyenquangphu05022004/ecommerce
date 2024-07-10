package com.example.ecommerce.service.dto;

import com.example.ecommerce.common.utils.SystemUtils;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@SuperBuilder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BasketDto extends BaseDto {
    private Integer quantity;
    private StockDto stock;
    private StockClassificationDto stockClassification;
    public String getFormatTotalPrice() {
        return SystemUtils.getFormatNumber(this.getTotalPrice());
    }
    private Integer getTotalPrice() {
        if(quantity != null && stock != null && stock.getPrice() != null) {
            return quantity * stock.getPrice();
        }
        return 0;
    }
}
