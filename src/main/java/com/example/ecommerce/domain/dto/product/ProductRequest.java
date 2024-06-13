package com.example.ecommerce.domain.dto.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {
    private MapName language;
    private Long categoryId;
    private String description;
}
