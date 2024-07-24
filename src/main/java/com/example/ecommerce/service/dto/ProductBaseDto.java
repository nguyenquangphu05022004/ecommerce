package com.example.ecommerce.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class ProductBaseDto extends BaseDto{
    private CategoryDto category;
    private BrandDto brand;
    private String name;
    private String description;
}
