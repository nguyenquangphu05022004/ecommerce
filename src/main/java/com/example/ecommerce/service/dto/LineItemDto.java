package com.example.ecommerce.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LineItemDto extends BaseDto {
    private StockDto stock;
    private StockClassificationDto stockClassification;
    private Integer quantity;

    public String getImageUrlRepresentation() {
        if(stock != null) {
            return stock.getImageUrlRepresentation();
        }
        return null;
    }
}
