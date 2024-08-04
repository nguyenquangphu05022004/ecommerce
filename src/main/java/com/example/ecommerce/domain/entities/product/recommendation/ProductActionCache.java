package com.example.ecommerce.domain.entities.product.recommendation;

import com.example.ecommerce.domain.entities.BaseEntity;
import com.example.ecommerce.domain.entities.auth.User;
import com.example.ecommerce.domain.entities.product.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Table(name = "product_action_cache")
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class ProductActionCache extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Enumerated(EnumType.STRING)
    private TypeAction typeAction;
    @OneToMany(mappedBy = "productActionCache")
    private List<ProductSimilarity> productSimilarities;
}
