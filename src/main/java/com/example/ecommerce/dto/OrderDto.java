package com.example.ecommerce.dto;

import com.example.ecommerce.entity.Payment;
import com.example.ecommerce.entity.User;
import lombok.*;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDto extends BaseDto{

    private UserDto user;
    private ProductDto product;
    private Integer quantity;
    private Payment payment;
    private BillDto billDto;
    private Integer totalPrice;
}
