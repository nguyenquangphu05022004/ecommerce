package com.example.ecommerce.domain.model.binding.order;

import com.example.ecommerce.common.InvalidMessage;
import com.example.ecommerce.domain.entities.order.Payment;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor
@Setter
@Builder
@AllArgsConstructor
public class OrderRequest {
    @NotNull(message = InvalidMessage.NOT_NULL)
    private Payment payment;
    @NotNull(message = InvalidMessage.NOT_NULL)
    private Set<LineItemRequest> lineItems;
}
