package com.example.ecommerce.domain.model.binding;

import com.example.ecommerce.common.InvalidMessage;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartRequest {
    @NotNull(message = InvalidMessage.NOT_NULL)
    private Long inventoryId;
    @NotNull(message = InvalidMessage.NOT_NULL)
    private Integer quantity;
}
