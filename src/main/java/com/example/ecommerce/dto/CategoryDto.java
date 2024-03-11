package com.example.ecommerce.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Getter
@ToString
@AllArgsConstructor
public class CategoryDto extends BaseDto{
    private String name;
//    private List<ProductDto> products =new ArrayList<>();
}
