package com.example.ecommerce.frame.websocket.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "web.websocket")
public class WebSocketProperties {
    private String endpoint;
    private String appPrefix;
    private String brokerPrefix;
    private String uiDomain;
}
