package com.example.ecommerce.domain.entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@Embeddable
@AllArgsConstructor
public class EntityType {
    @Enumerated(EnumType.STRING)
    @NotNull
    private Type entityType;
    @NotNull
    private Long entityId;

    public  enum Type {
        USER, CUSTOMER, VENDOR, ORDER, PRODUCT, PRODUCT_INVENTORY, GROUP, MESSAGE,
        CATEGORY, EVALUATION;
    }
}
