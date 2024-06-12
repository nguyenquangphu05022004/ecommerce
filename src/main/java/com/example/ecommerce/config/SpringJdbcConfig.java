package com.example.ecommerce.config;

import com.example.ecommerce.domain.dto.utilize.Resources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@PropertySource("classpath:application.properties")
@Configuration
public class SpringJdbcConfig {

    private final Resources resources;

    @Autowired
    public SpringJdbcConfig(Resources resources) {
        this.resources = resources;
    }

    public  DataSource mysqlDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(resources.getDriverDatabase());
        dataSource.setUrl(resources.getUrlDatabase());
        dataSource.setUsername(resources.getUsernameDatabase());
        dataSource.setPassword(resources.getPasswordDatabase());
        return dataSource;
    }
}
