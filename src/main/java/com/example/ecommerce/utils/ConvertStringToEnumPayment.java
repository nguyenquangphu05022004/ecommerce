package com.example.ecommerce.utils;

import com.example.ecommerce.entity.Payment;
import com.example.ecommerce.entity.Role;
import org.springframework.core.convert.converter.Converter;

public class ConvertStringToEnumPayment implements Converter<String, Payment> {
    @Override
    public Payment convert(String source) {
        try {
            return Payment.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException ex) {
            return null;
        }
    }
}
