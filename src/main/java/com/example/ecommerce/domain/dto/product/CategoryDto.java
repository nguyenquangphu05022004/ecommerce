package com.example.ecommerce.domain.dto.product;

import com.example.ecommerce.domain.dto.BaseDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Getter
@ToString
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
public class CategoryDto extends BaseDto {
    private String name;
    private ImageDto image;
}
