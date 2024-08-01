package com.example.ecommerce.domain.model.binding;

import com.example.ecommerce.domain.entities.order.Payment;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
@Data
@NoArgsConstructor
public class OrderRequest {
    @NotNull
    private Payment payment;
    @NotNull
    private Set<LineItemRequest> lineItems;
    @Data
    @NoArgsConstructor
    public static class LineItemRequest {
        @NotNull
        private Long vendorId;
        private Long couponId;
        @NotNull
        private List<ItemRequest> items;
    }
    @Data
    @NoArgsConstructor
    public static class ItemRequest {
        @NotNull
        private Integer quantity;
        @NotNull
        private Long inventoryId;
    }
}
