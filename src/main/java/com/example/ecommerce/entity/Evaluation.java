package com.example.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "evaluations")
@NoArgsConstructor
@Data
@Getter
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
    private Integer numberOfLike;
}
