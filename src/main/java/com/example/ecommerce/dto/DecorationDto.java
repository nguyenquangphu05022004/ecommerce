package com.example.ecommerce.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class DecorationDto extends BasketDto{
    private String size;
    private String color;
}
