package com.hiwijaya.springcrud.app;

import com.hiwijaya.springcrud.config.ApplicationConfig;
import com.hiwijaya.springcrud.entity.Customer;
import com.hiwijaya.springcrud.service.BookService;
import com.hiwijaya.springcrud.service.CustomerService;
import com.hiwijaya.springcrud.util.Gender;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.sql.SQLException;

/**
 * @author Happy Indra Wijaya
 */
public class Main {


    public void xmlApplication() throws SQLException {
        ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
        BookService bookService = context.getBean(BookService.class);

        bookService.save();
    }


    public void annotationApplication() throws SQLException {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        CustomerService customerService = context.getBean(CustomerService.class);

        Customer c = new Customer();
        c.setName("Happy Indra Wijaya");
        c.setGender(Gender.MALE);

        customerService.simpan(c);
    }

    public static void main(String[] args) {
        Main main = new Main();

        try {
            main.annotationApplication();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

}
