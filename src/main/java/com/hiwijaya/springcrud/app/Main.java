package com.hiwijaya.springcrud.app;

import com.hiwijaya.springcrud.config.AnnotationConfig;
import com.hiwijaya.springcrud.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import java.sql.SQLException;

/**
 * @author Happy Indra Wijaya
 */
@Component
public class Main {


    public void xmlApplication() throws SQLException {
        ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
        BookService bookService = context.getBean(BookService.class);

        bookService.save();
    }


    public void annotationApplication() throws SQLException {
        ApplicationContext context = new AnnotationConfigApplicationContext(AnnotationConfig.class);
        BookService bookService = context.getBean(BookService.class);

        bookService.save();
    }

    public static void main(String[] args) {
        Main main = new Main();

        try {
            main.xmlApplication();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

}
