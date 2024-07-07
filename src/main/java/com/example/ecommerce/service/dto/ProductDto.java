package com.example.ecommerce.service.dto;

import com.example.ecommerce.domain.Language;
import com.example.ecommerce.domain.Stock;
import com.example.ecommerce.domain.dto.BaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDto extends BaseDto {
    private String nameVn;
    private String categoryName;
    private String description;
    private List<EvaluationDto> evaluations;
    private List<StockDto> stocks;
    private String brandName;

    public String getImageUrlRepresentation() {
        if(stocks != null) {
            return stocks.get(0).getImageUrlRepresentation();
        }
        return null;
    }

}
