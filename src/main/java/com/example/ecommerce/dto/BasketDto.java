package com.example.ecommerce.dto;

import com.example.ecommerce.utils.SystemUtils;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@SuperBuilder(toBuilder = true)
public class BasketDto extends BaseDto{
    private ProductDto product;
    private Integer quantity;
    private Integer totalPrice;

    public String getFormatTotalPrice() {
        return SystemUtils.getFormatNumber(this.totalPrice);
    }
}
