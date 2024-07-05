package com.example.ecommerce.domain.dto;

import com.example.ecommerce.common.utils.SystemUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class ProductDto extends BaseDto {
    private CategoryDto category;
    private VendorDto vendor;
    private String description;
    private MapName language;
    private List<EvaluationDto> evaluations = new ArrayList<>();
    private int numberOfProductSold;
    private List<StockResponse> stocks = new ArrayList<>();

    public Integer getAverageEvaluation() {
        Integer totalRate = 0;
        for(int i = 0; i < evaluations.size(); i++) totalRate += evaluations.get(i).getRating();
        return totalRate == 0 ? 0 :  totalRate/evaluations.size();
    }

    public String getFormatPrice() {
        if (this.stocks.size() == 0) return "0";
        return SystemUtils.getFormatNumber(this.stocks.get(0).getPrice());
    }
}
