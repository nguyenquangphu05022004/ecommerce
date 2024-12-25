package com.example.ecommerce.trade.frame;

import com.example.ecommerce.frame.web.swagger.SwaggerConfig;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class TradeConfiguration {
    @Bean
    public GroupedOpenApi tradeGroupOpenApi() {
        return SwaggerConfig.buildGroupedOpenApi("trade");
    }
}
