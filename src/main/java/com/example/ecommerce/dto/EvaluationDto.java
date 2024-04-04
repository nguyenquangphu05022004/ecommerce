package com.example.ecommerce.dto;

import com.example.ecommerce.entity.Product;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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

    public String getFormatDate(){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return format.format(getModifiedDate());
    }
}
