package com.example.ecommerce.config;

import com.example.ecommerce.utils.ConvertStringToEnumPayment;
import com.example.ecommerce.utils.ConvertStringToEnumRole;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new ConvertStringToEnumRole());
        registry.addConverter(new ConvertStringToEnumPayment());
    }
}
