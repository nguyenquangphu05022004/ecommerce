package com.example.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@NoArgsConstructor
@Getter
@Data
public class Product extends Base{

    /*
        product:
            id, name, thumbnail, price,category, vendor,
            description, listEvaluation, listFeedBack
     */

    private String name;
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
