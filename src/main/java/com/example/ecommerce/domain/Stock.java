package com.example.ecommerce.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "stocks")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class Stock extends BaseEntity {
    private Integer price;
    private String code;
    @Enumerated(EnumType.STRING)
    private Color color;
    @OneToMany(cascade = CascadeType.ALL)
    private List<StockClassification> stockClassifications = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Image> images = new ArrayList<>();
    @OneToMany(mappedBy = "stock")
    private List<Order> orders = new ArrayList<>();
}
