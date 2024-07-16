package com.example.ecommerce.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class LineItemDto extends BaseDto {
    private StockDto stock;
    private StockClassificationDto stockClassification;
    private Integer quantity;

//    public String getImageUrlRepresentation() {
//        if(stock != null) {
//            return stock.getImageUrlRepresentation();
//        }
//        return null;
//    }
}
