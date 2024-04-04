package com.example.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Table(name = "images")
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@Getter
public class Image extends Base{
    private String name;
    private String shortUrl;
    @OneToOne(mappedBy = "avatar")
    private User user;
    @OneToOne(mappedBy = "thumbnail")
    private Category category;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Image(String name, String shortUrl) {
        this.name = name; this.shortUrl = shortUrl;
    }
    public Image(Long id, String name, String shortUrl) {
        this(name, shortUrl);
        setId(id);
    }
}
