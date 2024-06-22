package com.example.ecommerce.domain.dto;

import com.example.ecommerce.domain.dto.BaseDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder(toBuilder = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MapName extends BaseDto {
    private String nameVn;
    private String nameEn;
}
