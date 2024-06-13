package com.example.ecommerce.domain.dto.product;

import com.example.ecommerce.domain.dto.BaseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder(toBuilder = true)
public class TrackProductSellerDto extends BaseDto {
    private Integer numberOfProductsSold;
    private ProductResponseTrack product;
    @Getter
    @Setter
    public static class ProductResponseTrack {
        private Long id;
        private MapName language;
        private List<StockResponseTrack> stocks;
        @Getter
        @Setter
        public static class StockResponseTrack {
            private Integer price;
        }
    }

}
