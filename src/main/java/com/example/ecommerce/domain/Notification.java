package com.example.ecommerce.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "notifications")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Notification extends BaseEntity {
    private String message;
    private Long entityId;
    @Enumerated(EnumType.STRING)
    private EntityType type;
}
