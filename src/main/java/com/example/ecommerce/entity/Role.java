package com.example.ecommerce.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER, VENDOR, ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
