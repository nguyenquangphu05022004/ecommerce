package com.example.ecommerce.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder(toBuilder = true)
public class Category extends Base{

    @Column(nullable = false, length = 100)
    private String name;

    @OneToOne
    @JoinColumn(name = "image_id")
    private Image image;

    @OneToMany(mappedBy = "category")
    private List<Product> products = new ArrayList<>();
}
