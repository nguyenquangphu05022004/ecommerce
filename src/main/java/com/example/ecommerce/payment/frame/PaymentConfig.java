package com.example.ecommerce.payment.frame;

import com.example.ecommerce.frame.web.swagger.SwaggerConfig;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class PaymentConfig {
    @Bean
    public GroupedOpenApi paymentGroupOpenApi() {
        return SwaggerConfig.buildGroupedOpenApi("payment");
    }
}
