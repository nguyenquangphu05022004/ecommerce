package com.example.ecommerce.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class LineItemDto extends BaseDto {

    private VendorDto vendor;
    private Set<ItemDto> items;
    private VendorDto.CouponDto coupon;
    @Getter
    @Setter
    @SuperBuilder(toBuilder = true)
    @NoArgsConstructor
    public static class ItemDto extends BaseDto {
        private StockDto stock;
        private StockClassificationDto stockClassification;
        private Integer quantity;
    }
}
