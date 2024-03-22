package com.example.ecommerce.utils;

import com.example.ecommerce.entity.Role;
import org.springframework.core.convert.converter.Converter;

public class ConvertStringToEnumRole implements Converter<String, Role> {
    @Override
    public Role convert(String s) {
        try {
            return Role.valueOf(s.toUpperCase());
        } catch (IllegalArgumentException ex) {
            return null;
        }
    }
}
