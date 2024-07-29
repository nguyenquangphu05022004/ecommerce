package com.example.ecommerce.domain.entities;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public interface SlugLink {
    String slugName = null;
}
