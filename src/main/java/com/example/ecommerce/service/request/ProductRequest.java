package com.example.ecommerce.service.request;

import com.example.ecommerce.service.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
public class ProductRequest extends ProductDto {
    private Long categoryId;
    private String nameEn;
    private Long brandId;
}
