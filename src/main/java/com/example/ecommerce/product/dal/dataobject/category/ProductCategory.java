package com.example.ecommerce.product.dal.dataobject.category;

import com.example.ecommerce.frame.auditting.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Entity
@Table(name = "production_categories")
@NoArgsConstructor
@Getter
@SuperBuilder(toBuilder = true)
@Setter
public class ProductCategory extends BaseEntity {
    @Column(unique = true)
    private String name;
    @OneToMany(mappedBy = "categoryParent")
    private Set<ProductCategory> children;

    @ManyToOne
    @JoinColumn(name = "category_parent_id")
    private ProductCategory categoryParent;

    private String thumbnail;

}
