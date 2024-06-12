package com.example.ecommerce.domain.dto.product;

import com.example.ecommerce.domain.dto.BaseDto;
import com.example.ecommerce.utils.SystemUtils;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class ProductDto extends BaseDto {
    private CategoryDto category;
    private StockResponse.ProductResponse.VendorResponse vendorResponse;
    private String description;
    private MapName language;
    private List<EvaluationDto> evaluations = new ArrayList<>();
    private TrackProductSellerDto productSeller;
    private List<StockResponse> stockResponses = new ArrayList<>();

    public Integer getAverageEvaluation() {
        Integer totalRate = 0;
        for(int i = 0; i < evaluations.size(); i++) totalRate += evaluations.get(i).getRating();
        return totalRate == 0 ? 0 :  totalRate/evaluations.size();
    }

    public String getFormatPrice() {
        if(this.stockResponses.size() == 0) return "0";
        return SystemUtils.getFormatNumber(this.stockResponses.get(0).getPrice());
    }
}