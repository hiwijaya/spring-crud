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
import java.util.Objects;
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
        dataSource.setDriverClassName(Objects.requireNonNull(props).getProperty("database.driverClassName"));
        dataSource.setJdbcUrl(props.getProperty("database.url"));
        dataSource.setUsername(props.getProperty("database.username"));
        dataSource.setPassword(props.getProperty("database.password"));

        return dataSource;

    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(Database.POSTGRESQL);
        vendorAdapter.setGenerateDdl(true);
        vendorAdapter.setShowSql(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.hiwijaya.springcrud.entity");
        factory.setDataSource(dataSource());

        // hibernate configuration properties (hibernate.cfg.xml)
        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.format_sql", "true");
        hibernateProperties.put("hibernate.jdbc.batch_size", "20");
        hibernateProperties.put("hibernate.order_inserts", "true");
        hibernateProperties.put("hibernate.order_updates", "true");
        hibernateProperties.put("hibernate.batch_versioned_data", "true");
        factory.setJpaProperties(hibernateProperties);

        return factory;

    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {

        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory);

        return txManager;

    }

}
