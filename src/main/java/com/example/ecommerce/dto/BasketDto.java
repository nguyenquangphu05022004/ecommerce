package com.example.ecommerce.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@SuperBuilder(toBuilder = true)
public class BasketDto extends BaseDto{
    private UserDto user;
    private ProductDto product;
    private Integer quantity;
    private Integer totalPrice;
}
