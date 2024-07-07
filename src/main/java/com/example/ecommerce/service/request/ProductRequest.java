package com.example.ecommerce.service.request;

import com.example.ecommerce.service.dto.ProductDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest extends ProductDto {
    private Long categoryId;
    private String nameEn;
}
