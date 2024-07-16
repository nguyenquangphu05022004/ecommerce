package com.example.ecommerce.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@Entity
@Table(name = "line_items")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class LineItem extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "stock_id")
    private Stock stock;

    @ManyToOne
    @JoinColumn(name = "stock_classificationId")
    private StockClassification stockClassification;

    @Column(nullable = false)
    private Integer quantity;
}
