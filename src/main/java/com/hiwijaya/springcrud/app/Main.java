package com.hiwijaya.springcrud.app;

import com.hiwijaya.springcrud.config.ApplicationConfig;
import com.hiwijaya.springcrud.entity.Book;
import com.hiwijaya.springcrud.entity.Customer;
import com.hiwijaya.springcrud.service.BookService;
import com.hiwijaya.springcrud.service.CustomerService;
import com.hiwijaya.springcrud.service.RentalService;
import com.hiwijaya.springcrud.util.BookUnavailableException;
import com.hiwijaya.springcrud.util.Gender;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author Happy Indra Wijaya
 */
public class Main {


    public void xmlApplication() throws SQLException {
        ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
        CustomerService customerService = context.getBean(CustomerService.class);

        List<Customer> customers = customerService.getAll();
        customers.stream().forEach(customer -> System.out.println(customer));
    }


    public void annotationApplication() throws SQLException {

        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        CustomerService customerService = context.getBean(CustomerService.class);
        BookService bookService = context.getBean(BookService.class);
        RentalService rentalService = context.getBean(RentalService.class);

        List<Customer> customers = customerService.getAll();
        List<Book> books = bookService.getAll();

        Customer customer = customers.get(0);

        Book book1 = books.get(0);
        Book book2 = books.get(1);
        Book book3 = books.get(2);

        books.forEach(System.out::println);

        BigDecimal bill = BigDecimal.ZERO;
        try {
            bill = rentalService.rent(customer, book1, book2, book3);
        } catch (BookUnavailableException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Bill: " + bill.toPlainString());


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
