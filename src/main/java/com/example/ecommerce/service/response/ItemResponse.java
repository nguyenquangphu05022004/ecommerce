package com.example.ecommerce.service.response;

import com.example.ecommerce.service.dto.StockDto;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ItemResponse {
    private StockDto stock;
    private Integer quantity;
    private LocalDateTime createdAt;
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof ItemResponse) {
            ItemResponse p = (ItemResponse) obj;
            if(p.getStock().getId().equals(this.stock.getId())) return true;
        }
        return false;
    }
}
