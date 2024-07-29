package com.example.ecommerce.domain.entities.product;

import com.example.ecommerce.domain.entities.BaseEntity;
import com.example.ecommerce.domain.Evaluation;
import com.example.ecommerce.domain.entities.auth.Vendor;
import com.example.ecommerce.domain.entities.file.ProductImage;
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
@Table(name = "products")
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder(toBuilder = true)
public class Product extends BaseEntity {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "language_id")
    private Language language;

    private int price;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
    @ManyToOne(cascade = CascadeType.ALL)

    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    @Column(columnDefinition = "text")
    private String description;

    @OneToMany(mappedBy = "product")
    private List<Evaluation> evaluations = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private ProductBrand productBrand;

    private boolean combination;

    @OneToMany
    @JoinColumn(name = "product_inventory_id")
    private Set<ProductInventory> productInventory;

    @OneToMany(mappedBy = "product")
    private List<ProductImage> images;

    @Entity
    @Table(name = "languages")
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Language extends BaseEntity {
        private String nameVn;
        private String nameEn;
    }

}



