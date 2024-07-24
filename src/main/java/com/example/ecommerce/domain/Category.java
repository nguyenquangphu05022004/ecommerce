package com.example.ecommerce.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private Set<Category> children;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @Entity
    @SuperBuilder(toBuilder = true)
    @Table(name = "categories_images")
    public static class CategoryImage extends FileEntity {
        @OneToOne
        @JoinColumn(name = "category_id")
        private Category category;
    }

}

