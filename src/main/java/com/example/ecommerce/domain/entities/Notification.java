package com.example.ecommerce.domain.entities;

import com.example.ecommerce.domain.entities.file.FileEntityType;
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
    private FileEntityType type;
}
