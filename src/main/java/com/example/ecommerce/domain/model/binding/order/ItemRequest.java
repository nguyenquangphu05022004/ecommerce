package com.example.ecommerce.domain.model.binding.order;

import com.example.ecommerce.common.InvalidMessage;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@NoArgsConstructor
@Setter
@Builder
@AllArgsConstructor
public class ItemRequest {
    @NotNull(message = InvalidMessage.NOT_NULL)
    private Integer quantity;
    @NotNull(message = InvalidMessage.NOT_NULL)
    private Long inventoryId;
}
