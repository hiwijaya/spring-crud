package com.hiwijaya.springcrud.config;

import com.hiwijaya.springcrud.util.Lib;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;


/**
 * @author Happy Indra Wijaya
 */
@Configuration
@ComponentScan(basePackages = "com.hiwijaya.springcrud.service")
public class AnnotationConfig {

    @Bean
    public HikariDataSource dataSource(){
        Properties props = Lib.getPropertiesFile("database.properties");

        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(props.getProperty("database.driverClassName"));
        dataSource.setJdbcUrl(props.getProperty("database.url"));
        dataSource.setUsername(props.getProperty("database.username"));
        dataSource.setPassword(props.getProperty("database.password"));

        return dataSource;

    }

}
