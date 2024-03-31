package com.example.ecommerce.dto;

import com.example.ecommerce.entity.*;
import com.example.ecommerce.utils.SystemUtils;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.web.multipart.MultipartFile;

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
    private LanguageDto language;
    private List<EvaluationDto> evaluations = new ArrayList<>();
    private MultipartFile multipartFile;
    private TrackProductSeller trackProductSeller;
    private Integer numberOfProduct;

    public Integer getAverageEvaluation() {
        Integer totalRate = 0;
        for(int i = 0; i < evaluations.size(); i++) totalRate += evaluations.get(i).getRating();
        return totalRate == 0 ? 0 :  totalRate/evaluations.size();
    }

    public String getFormatPrice() {
        return SystemUtils.getFormatNumber(this.price);
    }

}
