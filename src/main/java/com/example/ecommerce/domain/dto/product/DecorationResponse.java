package com.example.ecommerce.domain.dto.product;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class DecorationResponse extends BasketDto {
    private String size;
    private String color;
}
