package com.example.ecommerce.frame.web.swagger;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("web.swagger")
@Data
public class SwaggerProperties {


    @NotEmpty(message = "title can't be empty")
    private String title;

    @NotEmpty(message = "Description can't be empty")
    private String description;

    @NotEmpty(message = "author can't be empty")
    private String author;

    @NotEmpty(message = "version app can't empty")
    private String version;

    @NotEmpty(message = "admin dashboard can't be empty")
    private String url;

    @NotEmpty(message = "email can't be empty")
    private String email;


}