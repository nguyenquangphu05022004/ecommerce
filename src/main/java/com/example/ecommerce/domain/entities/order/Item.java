package com.example.ecommerce.domain.entities.order;
import com.example.ecommerce.domain.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
    @Table(name = "items")
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @SuperBuilder(toBuilder = true)
    @Setter
    public  class Item extends BaseEntity{
        @ManyToOne
        @JoinColumn(name = "stock_id")
        private Stock stock;
        @ManyToOne
        @JoinColumn(name = "stock_classificationId")
        private Stock.StockClassification stockClassification;
        @Column(nullable = false)
        private Integer quantity;
        @ManyToOne
        @JoinColumn(name = "line_item_id")
        private LineItem lineItem;
    }
