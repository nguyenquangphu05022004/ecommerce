package com.example.ecommerce.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Setter
@NoArgsConstructor
public class StockClassificationDto extends BaseDto {
    private Integer quantityOfProduct;
    private String sizeName;
    private int seller;
}
