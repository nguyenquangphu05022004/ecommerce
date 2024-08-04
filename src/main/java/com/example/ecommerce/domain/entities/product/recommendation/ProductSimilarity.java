package com.example.ecommerce.domain.entities.product.recommendation;

import com.example.ecommerce.domain.entities.BaseEntity;
import com.example.ecommerce.domain.entities.product.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "product_similarity")
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class ProductSimilarity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "product_action_cache_id")
    private ProductActionCache productActionCache;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private double similarity;
}
