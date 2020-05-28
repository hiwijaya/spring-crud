package com.hiwijaya.springcrud.service;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Happy Indra Wijaya
 */
@Component
public class BookService {

    // TODO: codes below is just for test connection and di config

    @Autowired
    private HikariDataSource dataSource;

    public void save() throws SQLException {
        Connection connection = dataSource.getConnection();
        if(connection != null){
            System.out.println("Database connected.");
        }
        System.out.println("Save");

    }

}
