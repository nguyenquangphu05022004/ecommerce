package com.example.ecommerce.service.dto;

import com.example.ecommerce.domain.dto.BaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StockClassificationDto extends BaseDto {
    private Integer quantityOfProduct;
    private String sizeName;
    private int seller;
}
