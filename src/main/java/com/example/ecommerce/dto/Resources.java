package com.example.ecommerce.dto;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
@Getter
public class Resources {
    @Value("${spring.datasource.driver-class-name}")
    private  String driverDatabase;
    @Value("${spring.datasource.url}")
    private String urlDatabase;
    @Value("${spring.datasource.username}")
    private String usernameDatabase;
    @Value("${spring.datasource.password}")
    private String passwordDatabase;
    @Value("${filesStorage.path}")
    private String urlFilesStorage;
}
