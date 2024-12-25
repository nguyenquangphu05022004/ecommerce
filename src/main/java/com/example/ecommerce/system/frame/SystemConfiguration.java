package com.example.ecommerce.system.frame;

import com.example.ecommerce.frame.web.swagger.SwaggerConfig;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class SystemConfiguration {
    @Bean
    public GroupedOpenApi systemGroupOpenApi() {
        return SwaggerConfig.buildGroupedOpenApi("system");
    }
}
