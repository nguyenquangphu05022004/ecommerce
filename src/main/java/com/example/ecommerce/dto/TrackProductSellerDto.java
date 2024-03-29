package com.example.ecommerce.dto;

import com.example.ecommerce.entity.Product;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Getter
@Data
@SuperBuilder(toBuilder = true)
public class TrackProductSellerDto extends BaseDto{
    private ProductDto product;
    private Integer numberOfProductsSold;
}
