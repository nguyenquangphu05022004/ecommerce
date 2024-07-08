package com.example.ecommerce.service.dto;

import com.example.ecommerce.domain.dto.BaseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class ProductDtoBase extends BaseDto {
    private String name;
    private String categoryName;
    private String description;
    private String brandName;
}
