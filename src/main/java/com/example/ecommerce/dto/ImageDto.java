package com.example.ecommerce.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class ImageDto extends BaseDto{
    private String name;
    private String shortUrl;
}
