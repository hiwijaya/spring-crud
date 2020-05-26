package com.hiwijaya.springcrud.app;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Happy Indra Wijaya
 */
public class XmlApp {

    public static void main(String[] args) throws SQLException {
        ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");

        HikariDataSource dataSource = context.getBean("dataSource", HikariDataSource.class);
        Connection connection = dataSource.getConnection();
        if(connection != null){
            System.out.println("Database connected.");
        }

    }

}
