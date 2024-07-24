package com.example.ecommerce.service.dto;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(toBuilder = true)
public class BrandDto extends BaseDto {
    private String name;
}
