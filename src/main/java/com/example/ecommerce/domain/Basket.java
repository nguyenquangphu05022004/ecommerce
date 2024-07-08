package com.example.ecommerce.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Setter
@NoArgsConstructor
@Getter
@SuperBuilder(toBuilder = true)
@Table(name = "basket")
public class Basket extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "stock_id")
    private Stock stock;
    @ManyToOne
    @JoinColumn(name = "stock_classification_id")
    private StockClassification stockClassification;

    private Integer quantity;
}
