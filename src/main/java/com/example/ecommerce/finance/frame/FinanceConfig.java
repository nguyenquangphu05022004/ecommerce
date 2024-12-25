package com.example.ecommerce.finance.frame;

import com.example.ecommerce.frame.web.swagger.SwaggerConfig;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class FinanceConfig {
    @Bean
    public GroupedOpenApi financeGroupOpenApi() {
        return SwaggerConfig.buildGroupedOpenApi("finance");
    }
}
