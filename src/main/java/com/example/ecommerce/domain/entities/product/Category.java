package com.example.ecommerce.domain.entities.product;

import com.example.ecommerce.domain.entities.BaseEntity;
import com.example.ecommerce.domain.entities.SlugLink;
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
public class Category extends BaseEntity implements SlugLink {
    @Column(nullable = false, length = 100)
    private String name;
    @OneToOne(mappedBy = "category",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private CategoryImage image;
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private Set<Category> children;

    private Boolean displayAtHomePage;

}

