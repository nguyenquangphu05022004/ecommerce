package com.example.ecommerce.statistic.frame;

import com.example.ecommerce.frame.web.swagger.SwaggerConfig;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StatisticFrameConfig {
    @Bean
    public GroupedOpenApi buildStatisticGroupOpenApi() {
        return SwaggerConfig.buildGroupedOpenApi("statistic");
    }
}
