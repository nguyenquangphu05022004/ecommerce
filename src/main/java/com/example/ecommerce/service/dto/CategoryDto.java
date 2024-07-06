package com.example.ecommerce.service.dto;

import com.example.ecommerce.domain.dto.BaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuperBuilder(toBuilder = true)
public class CategoryDto extends BaseDto {
    private String name;
    private String slug;
    private String imageUrl;
}
