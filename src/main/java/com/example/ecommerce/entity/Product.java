package com.example.ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder(toBuilder = true)
public class Product extends Base{

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "language_id")
    private Language language;
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;
    @Column(columnDefinition = "text")
    private String description;
    @OneToMany(mappedBy = "product")
    private List<Evaluation> evaluations = new ArrayList<>();
    @OneToMany(mappedBy = "product")
    private List<Order> orders = new ArrayList<>();
    @OneToOne(mappedBy = "product")
    private TrackProductSeller productSeller;
    @OneToMany(mappedBy = "product")
    private List<Stock> stocks = new ArrayList<>();
}
