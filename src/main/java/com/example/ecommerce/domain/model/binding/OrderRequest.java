package com.example.ecommerce.domain.model.binding;

import com.example.ecommerce.common.InvalidMessage;
import com.example.ecommerce.domain.entities.Payment;
import jakarta.validation.constraints.NotNull;
import lombok.*;

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
