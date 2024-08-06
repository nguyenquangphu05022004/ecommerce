package com.example.ecommerce.service.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder(toBuilder = true)
public class BrandDto extends BaseDto {
    private String name;
}
