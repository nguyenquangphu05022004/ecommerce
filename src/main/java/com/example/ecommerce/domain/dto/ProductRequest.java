package com.example.ecommerce.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {
    private MapName language;
    private Long categoryId;
    private String description;
}
