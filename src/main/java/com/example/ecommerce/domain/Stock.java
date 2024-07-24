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
    private String color;
    @OneToMany(mappedBy = "stock", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StockClassification> stockClassifications = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToMany(mappedBy = "stock",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StockImage> images = new ArrayList<>();

    @Entity
    @Table(name = "stock_images")
    @NoArgsConstructor
    @AllArgsConstructor
    @SuperBuilder(toBuilder = true)
    @Getter
    public static class StockImage extends FileEntity {
        @ManyToOne
        @JoinColumn(name = "stock_id")
        private Stock stock;
    }
}

