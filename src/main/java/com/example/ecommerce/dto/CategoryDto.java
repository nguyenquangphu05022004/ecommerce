package com.example.ecommerce.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Getter
@ToString
@SuperBuilder(toBuilder = true)

@AllArgsConstructor
public class CategoryDto extends BaseDto{
    private String name;
//    private List<ProductDto> products =new ArrayList<>();
}
