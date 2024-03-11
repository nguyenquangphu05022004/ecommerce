package com.example.ecommerce.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class BasketDto extends BaseDto{
    private UserDto user;
    private ProductDto product;
    private Integer quantity;
    private Integer totalPrice;
}
