package com.example.ecommerce.frame.web.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "web.ui")
@Data
public class WebProperties {

    private Api adminApi = new Api("/admin-api", "**.controller.admin.**");
    private Api appApi = new Api("/app-api","**.controller.app.**");
    private UI adminUi;
    private String localDomain;
    private String prodDomain;
    private String walletPath;

    @Data
    @AllArgsConstructor
    public static class Api {
        private String prefix;
        private String controller;
    }
    @Data
    public static class UI {
        private String url;
    }
}
