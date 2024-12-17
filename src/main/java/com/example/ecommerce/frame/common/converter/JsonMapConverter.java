package com.example.ecommerce.frame.common.converter;

import jakarta.persistence.AttributeConverter;

import java.util.Map;

public class JsonMapConverter implements AttributeConverter<Map<String, Object>, String> {
    @Override
    public String convertToDatabaseColumn(Map<String, Object> attribute) {
        return null;
    }

    @Override
    public Map<String, Object> convertToEntityAttribute(String dbData) {
        return null;
    }
}
