package com.example.ecommerce.domain.entities;

import jakarta.persistence.*;
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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FileEntity> images;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Evaluation parent;

    @OneToMany(mappedBy = "parent")
    private List<Evaluation> evaluations;

}
