package com.example.ecommerce.dto;

import com.example.ecommerce.entity.Payment;
import com.example.ecommerce.entity.User;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@SuperBuilder(toBuilder = true)
public class OrderDto extends BaseDto{

    private UserDto user;
    private ProductDto product;
    private Integer quantity;
    private Payment payment;
    private String status;
    private Integer totalPrice;
}
