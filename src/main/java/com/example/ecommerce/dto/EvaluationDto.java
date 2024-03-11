package com.example.ecommerce.dto;

import com.example.ecommerce.entity.Product;
import lombok.*;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EvaluationDto extends BaseDto {
    private Integer rating;
    private Product product;
    private String content;
    private UserDto user;
    private Integer numberOfLike;
}
