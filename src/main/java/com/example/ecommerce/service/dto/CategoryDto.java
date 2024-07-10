package com.example.ecommerce.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto extends BaseDto {
    private String name;
    private String slug;
    private String imageUrl;
}
