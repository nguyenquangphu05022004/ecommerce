package com.example.ecommerce.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "stock_classifications")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class StockClassification extends BaseEntity {
    private Integer quantityOfProduct;
    @Enumerated(EnumType.STRING)
    private Size size;
    private int seller;
}
