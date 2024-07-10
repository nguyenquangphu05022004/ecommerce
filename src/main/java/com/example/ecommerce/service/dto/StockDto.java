package com.example.ecommerce.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StockDto extends BaseDto {
    private Integer price;
    private String code;
    private String color;
    private ProductDtoBase product;
    private List<StockClassificationDto> stockClassifications;
    private List<String> imageUrls;

    public Integer getTotalStock() {
        if(stockClassifications != null) {

        }
        return null;
    }
    public String getImageUrlRepresentation() {
        if(imageUrls != null) {
           return imageUrls.get(0);
        }
        return null;
    }
}
