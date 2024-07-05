package com.example.ecommerce.domain.dto;

import com.example.ecommerce.domain.Color;
import com.example.ecommerce.common.utils.SystemUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class StockResponse extends BaseDto {
    private String code;
    private List<ImageDto> images = new ArrayList<>();
    private ProductResponse product;
    private Color color;
    private Integer price;
    private List<StockClassificationResponse> stockClassifications = new ArrayList<>();

    public String getFormatPrice() {
        return SystemUtils.getFormatNumber(price);
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProductResponse {
        private Long id;
        private MapName language;
        private VendorResponse vendor;
        private String description;

        @Getter
        @Setter
        @AllArgsConstructor
        @NoArgsConstructor
        public static class VendorResponse {
            private Long id;
            private String shopName;
            private Integer perMoneyDelivery;

            public String getFormatMoneyDelivery() {
                return SystemUtils.getFormatNumber(perMoneyDelivery);
            }
        }
    }
}


