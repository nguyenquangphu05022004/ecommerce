package com.example.ecommerce.domain.model.binding;

import com.example.ecommerce.common.InvalidMessage;
import com.example.ecommerce.domain.entities.order.Payment;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
@Data
@NoArgsConstructor
public class OrderRequest {
    @NotNull(message = InvalidMessage.NOT_NULL)
    private Payment payment;
    @NotNull(message = InvalidMessage.NOT_NULL)
    private Set<LineItemRequest> lineItems;
    @Data
    @NoArgsConstructor
    public static class LineItemRequest {
        @NotNull(message = InvalidMessage.NOT_NULL)
        private Long vendorId;
        private Long couponId;
        @NotNull(message = InvalidMessage.NOT_NULL)
        private List<ItemRequest> items;
    }
    @Data
    @NoArgsConstructor
    public static class ItemRequest {
        @NotNull(message = InvalidMessage.NOT_NULL)
        private Integer quantity;
        @NotNull(message = InvalidMessage.NOT_NULL)
        private Long inventoryId;
    }
}
