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
@Data
@SuperBuilder(toBuilder = true)
public class Product extends Base{

    /*
        product:
            id, name, thumbnail, price,category, vendor,
            description, listEvaluation, listFeedBack
     */

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "language_id")
    private Language language;
    private String thumbnail;
    private Integer price;
    private Integer quantity;
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
    private Basket basket;
    @OneToOne(mappedBy = "product")
    private TrackProductSeller productSeller;
}
