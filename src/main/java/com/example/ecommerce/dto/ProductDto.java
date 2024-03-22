package com.example.ecommerce.dto;

import com.example.ecommerce.entity.*;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class ProductDto extends BaseDto {
    private String thumbnail;
    private Integer price;
    private Integer quantity;
    private CategoryDto category;
    private VendorDto vendor;
    private String description;
    private Language language;
    private List<EvaluationDto> evaluations = new ArrayList<>();

    public Integer getAverageEvaluation() {
        Integer totalRate = 0;
        for(int i = 0; i < evaluations.size(); i++) totalRate += evaluations.get(i).getRating();
        return totalRate == 0 ? 0 :  totalRate/evaluations.size();
    }
    public String getFormatPrice() {
        String price = String.valueOf(this.price);
        int n = price.length();
        StringBuilder builder = new StringBuilder();
        while(n > 0) {
            StringBuilder rev = new StringBuilder(price.substring(n - 3 >= 0 ? n - 3 : 0, n));
            builder.append(rev.reverse());
            if(n - 3 > 0) builder.append(",");
            n -= 3;
        }
        return builder.reverse().toString();

    }

}
