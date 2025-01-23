package com.example.ecommerce.frame.common.converter;

import com.example.ecommerce.frame.common.collection.MapUtils;
import com.example.ecommerce.frame.common.json.JsonUtils;
import com.example.ecommerce.frame.common.string.StringUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.persistence.AttributeConverter;

import java.util.Map;

public class JsonMapConverter implements AttributeConverter<Map<String, Object>, String> {
    @Override
    public String convertToDatabaseColumn(Map<String, Object> attribute) {
        if(MapUtils.isEmpty(attribute)) {
            return null;
        }
        return JsonUtils.write(attribute);
    }

    @Override
    public Map<String, Object> convertToEntityAttribute(String dbData) {
        if(dbData == null || dbData.isEmpty()) {
            return null;
        }
        return JsonUtils.parseObject(dbData, new TypeReference<Map<String, Object>>() {});
    }
}
