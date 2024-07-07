package com.example.ecommerce.domain;

import jakarta.persistence.*;
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
    @ManyToOne
    @JoinColumn(name = "size_id")
    private Size size;
    private int seller;
    @ManyToOne
    @JoinColumn(name = "stock_id")
    private Stock stock;
}
