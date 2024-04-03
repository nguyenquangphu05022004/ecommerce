package com.example.ecommerce.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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
}
