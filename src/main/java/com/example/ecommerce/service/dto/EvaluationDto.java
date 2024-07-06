package com.example.ecommerce.service.dto;

import com.example.ecommerce.domain.dto.BaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuperBuilder
public class EvaluationDto extends BaseDto {
    private Integer rating;
    private Long productId;
    private String content;
    private Long userId;
    private List<String> imageUrls;
}
