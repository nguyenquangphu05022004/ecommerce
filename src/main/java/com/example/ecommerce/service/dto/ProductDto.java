package com.example.ecommerce.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.NoSuchElementException;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDto extends ProductDtoBase {
    private List<EvaluationDto> evaluations;
    private List<StockDto> stocks;

    public Integer getAverageRate() {
        if(evaluations == null) return 0;
        int sumRate = evaluations.stream().mapToInt(ev -> ev.getRating())
                .sum();
        return sumRate / evaluations.size();
    }
    public String getImageUrlRepresentation() {
        if(stocks != null) {
            return stocks.get(0).getImageUrlRepresentation();
        }
        return null;
    }
    public String getPriceRange() {
        if(stocks != null) {
            int max = stocks.stream().mapToInt(stock -> stock.getPrice())
                    .max()
                    .orElseThrow(NoSuchElementException::new);
            int min = stocks.stream().mapToInt(stock -> stock.getPrice())
                    .min()
                    .orElseThrow(NoSuchFieldError::new);
            return String.format("%s - %s Đồng", min, max);
        }
        return null;
    }

}
