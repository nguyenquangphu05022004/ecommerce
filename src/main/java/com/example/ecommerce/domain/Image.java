package com.example.ecommerce.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "images")
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@Getter
@Setter
public class Image extends Base{
    private String name;
    private String shortUrl;

    public Image(String name, String shortUrl) {
        this.name = name; this.shortUrl = shortUrl;
    }
    public Image(Long id, String name, String shortUrl) {
        this(name, shortUrl);
        setId(id);
    }
}
