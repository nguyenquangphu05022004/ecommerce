package com.example.ecommerce.promotion.frame;

import com.example.ecommerce.frame.web.swagger.SwaggerConfig;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PromotionConfig {

    @Bean
    public GroupedOpenApi buildPromotionGroupOpenApi() {
        return SwaggerConfig.buildGroupedOpenApi("promotion");
    }
}
