package com.example.ecommerce.dto;

import com.example.ecommerce.entity.Category;
import com.example.ecommerce.entity.Evaluation;
import com.example.ecommerce.entity.Order;
import com.example.ecommerce.entity.Vendor;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto extends BaseDto {
    private String name;
    private String thumbnail;
    private Integer price;
    private Integer quantity;
    private CategoryDto category;
    private VendorDto vendor;
    private String description;
    private List<EvaluationDto> evaluations = new ArrayList<>();

    public String getShortName() {
        if (name != null && name.length() > 15) {
            return name.substring(0, 15).concat("...");
        }
        return name;
    }

    public String getShortDescription() {
        if (description != null && description.length() > 15) {
            return description.substring(0, 15).concat("...");
        }
        return description;
    }
    public Integer getAverageEvaluation() {
        Integer totalRate = 0;
        for(int i = 0; i < evaluations.size(); i++) totalRate += evaluations.get(i).getRating();
        return totalRate/5;
    }

}
