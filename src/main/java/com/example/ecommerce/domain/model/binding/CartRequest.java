package com.example.ecommerce.domain.model.binding;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartRequest {
    @NotNull
    private InventoryRequest inventoryRequest;
    @NotNull
    private Integer quantity;
}
