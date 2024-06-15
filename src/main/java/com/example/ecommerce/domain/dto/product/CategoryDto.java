package com.example.ecommerce.domain.dto.product;

import com.example.ecommerce.domain.dto.BaseDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Getter
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@Setter
public class CategoryDto extends BaseDto {
    private String name;
    private ImageDto image;
    private int numberOfProduct;
}
