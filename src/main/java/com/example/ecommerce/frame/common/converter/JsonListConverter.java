package com.example.ecommerce.frame.common.converter;

import com.example.ecommerce.frame.common.collection.CollUtils;
import com.example.ecommerce.frame.common.json.JsonUtils;
import com.example.ecommerce.frame.common.string.StringUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Collections;
import java.util.List;

public class JsonListConverter implements AttributeConverter<List<String>, String> {
    @Override
    public String convertToDatabaseColumn(List<String> attribute) {
        if(CollUtils.isEmpty(attribute)) return null;
        return JsonUtils.write(attribute);
    }

    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        if(dbData == null || dbData.isEmpty()) {
            return Collections.emptyList();
        } else {
            return JsonUtils.parseObject(dbData, new TypeReference<List<String>>() {});
        }
    }
}
