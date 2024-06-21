package com.example.ecommerce.domain.dto.product;

import com.example.ecommerce.domain.dto.BaseDto;
import com.example.ecommerce.domain.dto.ENUM.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockClassificationResponse extends BaseDto {
    private Integer quantityOfProduct;
    private Size size;
    private int seller;

    public int getQuantityCurrent() {
        if(quantityOfProduct != null && quantityOfProduct > seller)
            return quantityOfProduct - seller;
        return 0;
    }
}
