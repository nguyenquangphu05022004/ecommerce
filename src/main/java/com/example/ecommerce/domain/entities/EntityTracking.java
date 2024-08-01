package com.example.ecommerce.domain.entities;

import com.example.ecommerce.domain.entities.BaseEntity;
import com.example.ecommerce.domain.entities.EntityType;
import com.example.ecommerce.domain.entities.product.EntityAction;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product_tracking")
@Getter
@Setter
@NoArgsConstructor
public class EntityTracking extends BaseEntity {
    @Enumerated(EnumType.STRING)
    private EntityAction productAction;
    @Embedded
    private EntityType entityType;
}
