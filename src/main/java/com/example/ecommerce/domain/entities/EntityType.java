package com.example.ecommerce.domain.entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@Embeddable
public class EntityType {
    @Enumerated(EnumType.STRING)
    private Type entityType;
    private Long entityId;

    public  enum Type {
        CUSTOMER, VENDOR, ORDER,
    }
}
