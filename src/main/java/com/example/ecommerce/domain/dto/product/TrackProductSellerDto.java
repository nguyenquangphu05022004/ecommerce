package com.example.ecommerce.domain.dto.product;

import com.example.ecommerce.domain.dto.BaseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder(toBuilder = true)
public class TrackProductSellerDto extends BaseDto {
    private Integer numberOfProductsSold;
}
