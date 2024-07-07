package com.example.ecommerce.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "colors")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Color extends BaseEntity{
    @Column(unique = true)
    private String name;
    @OneToOne(mappedBy = "color", cascade = CascadeType.ALL, orphanRemoval = true)
    private ColorImage colorImage;
}
@Entity
@Table(name = "color_images")
@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
class ColorImage extends FileEntity{
    @OneToOne
    @JoinColumn(name = "color_id")
    private Color color;
}
