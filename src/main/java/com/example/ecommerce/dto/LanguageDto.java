package com.example.ecommerce.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder(toBuilder = true)
@Getter
@Data
@NoArgsConstructor
public class LanguageDto extends BaseDto{
    private String nameVn;
    private String nameEn;
}
