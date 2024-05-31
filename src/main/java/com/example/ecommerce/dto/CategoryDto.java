package com.example.ecommerce.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@Getter
@ToString
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
public class CategoryDto extends BaseDto{
    private String name;
    private ImageDto thumbnail;
}
