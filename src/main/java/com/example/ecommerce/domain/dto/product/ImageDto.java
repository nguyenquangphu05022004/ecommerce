package com.example.ecommerce.domain.dto.product;

import com.example.ecommerce.domain.dto.BaseDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class ImageDto extends BaseDto {
    private String name;
    private String shortUrl;
}
