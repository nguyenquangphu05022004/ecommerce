package com.example.ecommerce.domain;

import jakarta.persistence.*;
import lombok.Data;
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

public class Evaluation extends Base {
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
    @OneToMany(cascade = CascadeType.ALL)
    private List<Image> images = new ArrayList<>();
}
