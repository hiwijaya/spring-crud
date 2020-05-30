package com.hiwijaya.springcrud.config;

import com.hiwijaya.springcrud.util.Lib;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import java.util.Properties;


/**
 * @author Happy Indra Wijaya
 */
@Configuration
@ComponentScan(basePackages = "com.hiwijaya.springcrud.service")
@EnableJpaRepositories(basePackages = "com.hiwijaya.springcrud.repository")
@EnableTransactionManagement
public class ApplicationConfig {

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

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        vendorAdapter.setDatabase(Database.POSTGRESQL);
        vendorAdapter.setGenerateDdl(true);
        vendorAdapter.setShowSql(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.hiwijaya.springcrud.entity");
        factory.setDataSource(dataSource());

        return factory;

    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {

        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory);

        return txManager;

    }

}
