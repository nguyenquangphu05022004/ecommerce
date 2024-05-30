package com.example.ecommerce.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@SuperBuilder(toBuilder = true)
public class StockDto extends BaseDto{
    private String code;
    private Integer quantity;
    private List<ImageDto> imageDtos = new ArrayList<>();
    private ProductDto productDto;
}
