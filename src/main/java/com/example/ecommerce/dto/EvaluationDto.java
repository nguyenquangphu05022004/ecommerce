package com.example.ecommerce.dto;

import com.example.ecommerce.entity.Product;
import com.example.ecommerce.utils.SystemUtils;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)

public class EvaluationDto extends BaseDto {
    private Integer rating;
    private ProductDto product;
    private String content;
    private UserDto user;
    private Integer numberOfLike;
    private List<ImageDto> images =new ArrayList<>();

    public String getFormatDate(){
        return SystemUtils.getFormatDate(getModifiedDate(), "HH:mm:ss dd-MM-yyyy");
    }
}
