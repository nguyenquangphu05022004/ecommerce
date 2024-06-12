package com.example.ecommerce.domain.dto.product;

import com.example.ecommerce.domain.dto.BaseDto;
import com.example.ecommerce.utils.SystemUtils;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@SuperBuilder(toBuilder = true)
public class StockResponse extends BaseDto {
    private String code;
    private Integer quantityOfProduct;
    private List<ImageDto> images = new ArrayList<>();
    private ProductResponse productResponse;
    private DecorationResponse decoration;
    private Integer price;

    public String getFormatPrice() {
        return SystemUtils.getFormatNumber(price);
    }
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
            public String getFormatMoneyDelivery() {
                return SystemUtils.getFormatNumber(perMoneyDelivery);
            }
        }
    }
}
