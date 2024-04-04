package com.example.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder(toBuilder = true)
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LanguageDto extends BaseDto{
    private String nameVn;
    private String nameEn;
}
