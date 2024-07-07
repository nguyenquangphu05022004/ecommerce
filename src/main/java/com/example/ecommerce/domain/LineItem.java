package com.example.ecommerce.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@Entity
@Table(name = "line_items")
@NoArgsConstructor
@AllArgsConstructor
public class LineItem extends BaseEntity{
    @ManyToOne
    @JoinTable(name = "stock_id")
    private Stock stock;

    @ManyToOne
    @JoinColumn(name = "stock_classificationId")
    private StockClassification stockClassification;

    @Column(nullable = false)
    private Integer quantity;
}
