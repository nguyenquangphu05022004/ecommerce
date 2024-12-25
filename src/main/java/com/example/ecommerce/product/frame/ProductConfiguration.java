package com.example.ecommerce.product.frame;

import com.example.ecommerce.frame.web.swagger.SwaggerConfig;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class ProductConfiguration {
    @Bean
    public GroupedOpenApi productGroupOpenApi() {
        return SwaggerConfig.buildGroupedOpenApi("product");
    }
}
