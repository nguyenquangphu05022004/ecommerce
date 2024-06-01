package com.example.ecommerce.entity;

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
public class Stock extends Base{
    private Integer quantityOfProduct;
    private Integer price;
    private String code;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "decoration_id")
    private Decoration decoration;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Image> images = new ArrayList<>();

}
