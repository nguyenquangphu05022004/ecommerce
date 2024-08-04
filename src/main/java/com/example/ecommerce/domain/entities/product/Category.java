package com.example.ecommerce.domain.entities.product;

import com.example.ecommerce.domain.entities.BaseEntity;
import com.example.ecommerce.domain.entities.file.CategoryImage;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Entity
@Table(name = "product_categories")
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder(toBuilder = true)
public class Category extends BaseEntity  {
    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 100)
    private String slug;

    @OneToOne(mappedBy = "category")
    private CategoryImage image;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private Set<Category> children;

    private boolean displayAtHomePage;

    @OneToMany(mappedBy = "category")
    private Set<Product> products;

}

