package com.example.ecommerce.file;

import com.example.ecommerce.frame.web.swagger.SwaggerConfig;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileFrameConfig {
    @Bean
    public GroupedOpenApi createFileGroupOpenApi() {
        return SwaggerConfig.buildGroupedOpenApi("file");
    }
}
