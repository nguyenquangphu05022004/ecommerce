package com.example.ecommerce.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class StockDto extends BaseDto {
    private Integer price;
    private String code;
    private String color;
    private ProductDtoBase product;
    private List<StockClassificationDto> stockClassifications;
    private List<String> imageUrls;

//    public Integer getTotalStock() {
//        if(stockClassifications != null) {
//
//        }
//        return null;
//    }
//    public String getImageUrlRepresentation() {
//        if(imageUrls != null) {
//           return imageUrls.get(0);
//        }
//        return null;
//    }
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof StockDto) {
            StockDto p = (StockDto) obj;
            if(p.getId().equals(this.getId())) return true;
        }
        return false;
    }
}
