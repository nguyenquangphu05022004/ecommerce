package com.example.ecommerce.domain;

import jakarta.persistence.*;
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
public class Category extends BaseEntity {
    @Column(nullable = false, length = 100)
    private String name;

    @Column(unique = true)
    private String slug;

    @OneToOne(mappedBy = "category",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private CategoryImage image;

}
