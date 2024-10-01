package com.example.ecommerce.domain.model.binding;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.swing.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class ProductInventoryFilterRequest {
    @NotNull
    private Long productId;
    private Set<Long> attrMapValueId;
}
