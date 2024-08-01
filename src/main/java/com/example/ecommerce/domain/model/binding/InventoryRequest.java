package com.example.ecommerce.domain.model.binding;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InventoryRequest {
    @NotNull
    private Long productId;
    @NotEmpty
    private String attributeCombinationKey;
}
