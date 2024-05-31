package com.example.ecommerce.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class ProductTypeResponse extends BaseDto {
    private StockResponse stock;
    private DecorationDto decoration;
}
