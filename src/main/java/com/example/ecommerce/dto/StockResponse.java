package com.example.ecommerce.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@SuperBuilder(toBuilder = true)
public class StockResponse extends BaseDto{
    private String code;
    private Integer quantity;
    private List<ImageDto> images = new ArrayList<>();
    private ProductResponse productResponse;
    private DecorationDto productType;
    private Integer price;
    @Getter
    @Setter
    @Builder
    public static class ProductResponse {
        private Long id;
        private String name;
        private VendorResponse vendorResponse;
        private String description;
        @Getter
        @Setter
        @Builder
        public static class VendorResponse {
            private Long id;
            private String name;
            private Integer perMoneyDelivery;
        }
    }
}
