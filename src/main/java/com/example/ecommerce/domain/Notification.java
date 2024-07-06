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
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
