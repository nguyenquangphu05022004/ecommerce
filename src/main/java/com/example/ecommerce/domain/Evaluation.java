package com.example.ecommerce.domain;

import com.example.ecommerce.domain.entities.BaseEntity;
import com.example.ecommerce.domain.entities.auth.User;
import com.example.ecommerce.domain.entities.file.FileEntity;
import com.example.ecommerce.domain.entities.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Table(name = "evaluations")
@NoArgsConstructor
@Setter
@Getter
@SuperBuilder(toBuilder = true)
public class Evaluation extends BaseEntity {
    @Column(columnDefinition = "tinyint")
    private Integer rating;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @Column(columnDefinition = "text")
    private String content;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "evaluation",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EvaluationImage> images;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Evaluation parent;
    @OneToMany(mappedBy = "parent")
    private List<Evaluation> evaluations;

    @Entity
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Table(name = "evaluation_images")
    @SuperBuilder(toBuilder = true)
    public static class EvaluationImage extends FileEntity {
        @ManyToOne
        @JoinColumn(name = "evaluation_id")
        private Evaluation evaluation;
    }


}
