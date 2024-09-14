package com.example.ecommerce.domain.entities.product;

import com.example.ecommerce.domain.entities.BaseEntity;
import com.example.ecommerce.domain.entities.FileEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "product_categories")
@Getter
@Setter
@SuperBuilder(toBuilder = true)
public class Category extends BaseEntity  {
    @Column(nullable = false, length = 100, unique = true)
    private String name;

    @Column(nullable = false, length = 100, unique = true)
    private String slug;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FileEntity> images;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private Set<Category> children;

    @OneToMany(mappedBy = "category")
    private Set<Product> products;

    public Category() {
        this.images = new ArrayList<>();
        this.children = new HashSet<>();
    }
}

