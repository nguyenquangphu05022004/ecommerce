package com.example.ecommerce.realtime.frame;

import com.example.ecommerce.frame.web.swagger.SwaggerConfig;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RealTimeConfig {
    @Bean
    public GroupedOpenApi createRealTimeGroupOpenApi() {
        return SwaggerConfig.buildGroupedOpenApi("realtime");
    }
}
