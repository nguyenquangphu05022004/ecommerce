package com.example.ecommerce.service.request;

import com.example.ecommerce.domain.Order;
import com.example.ecommerce.domain.UserContactDetails;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {
    @NotNull
    private UserContactDetails userContactDetails;
    @NotNull
    private List<LineItemRequest> lineItemRequests;
    @NotNull
    private Order.Payment payment;
    @Data
    public static class LineItemRequest {
        @NotNull
        private Long couponId;
        @NotNull
        private Long vendorId;
        @NotNull
        private List<ItemRequest> itemRequests;
    }
    @Data
    public static class ItemRequest {
        @NotNull
        private Long stockId;
        @NotNull
        private Long classificationId;
        @NotNull
        private Integer quantity;
    }
}
