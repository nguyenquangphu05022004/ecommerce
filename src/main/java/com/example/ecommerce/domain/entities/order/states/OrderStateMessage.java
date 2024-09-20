package com.example.ecommerce.domain.entities.order.states;

import com.example.ecommerce.domain.entities.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "order_state_messages")
@Getter
@SuperBuilder
@NoArgsConstructor
public class OrderStateMessage extends BaseEntity {
    private String message;
}
