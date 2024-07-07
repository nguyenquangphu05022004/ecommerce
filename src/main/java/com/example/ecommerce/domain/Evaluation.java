package com.example.ecommerce.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
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
    private List<EvaluationImage> images = new ArrayList<>();
}
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_evaluation_images")
class EvaluationImage extends FileEntity{
    @ManyToOne
    @JoinColumn(name = "evaluation_id")
    private Evaluation evaluation;
}
