package com.example.ecommerce.domain.entities.order.states.item;

import com.example.ecommerce.domain.entities.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Table(name = "line_item_messages")
@Entity
@SuperBuilder
@Getter
@NoArgsConstructor
public class LineItemMessage extends BaseEntity {
    private String message;
    private boolean isHeader;
}
