package com.hiwijaya.springcrud.app;

import com.hiwijaya.springcrud.config.ApplicationConfig;
import com.hiwijaya.springcrud.entity.Book;
import com.hiwijaya.springcrud.entity.Customer;
import com.hiwijaya.springcrud.entity.RentTransaction;
import com.hiwijaya.springcrud.service.BookService;
import com.hiwijaya.springcrud.service.CustomerService;
import com.hiwijaya.springcrud.service.RentalService;
import com.hiwijaya.springcrud.util.BookUnavailableException;
import com.hiwijaya.springcrud.util.Gender;
import com.hiwijaya.springcrud.util.RentOutdatedException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Happy Indra Wijaya
 */
public class Main {

    private CustomerService customerService;
    private BookService bookService;
    private RentalService rentalService;


    private void initService(ApplicationContext context){

        customerService = context.getBean(CustomerService.class);
        bookService = context.getBean(BookService.class);
        rentalService = context.getBean(RentalService.class);

    }

    private void createCustomers(){
        Customer customer1 = new Customer(null, "Liam Abraham Wijaya", Gender.MALE);
        Customer customer2 = new Customer(null, "Emma Watson", Gender.FEMALE);
        Customer customer3 = new Customer(null, "John Wick", Gender.MALE);

        customerService.save(customer1, customer2, customer3);

        customerService.getAll().forEach(System.out::println);

    }



    private void createBooks(){
        Book book1 = new Book(null,
                "The Fellowship of The Ring",
                "J. R. R. Tolkien",
                new BigDecimal(50000),
                false);

        Book book2 = new Book(null,
                "The Two Tower",
                "J. R. R. Tolkien",
                new BigDecimal(50000),
                false);

        Book book3 = new Book(null,
                "Return of The King",
                "J. R. R. Tolkien",
                new BigDecimal(60000),
                false);

        Book book4 = new Book(null,
                "The Hunger Games",
                "Suzanne Collins",
                new BigDecimal(30000),
                false);

        Book book5 = new Book(null,
                "Catching Fire",
                "Suzanne Collins",
                new BigDecimal(30000),
                false);

        Book book6 = new Book(null,
                "Mockingjay",
                "Suzanne Collins",
                new BigDecimal(45000),
                false);

        bookService.save(book1, book2, book3, book4, book5, book6);

        bookService.getAll().forEach(System.out::println);

    }

    private void rent(){
        List<Customer> customers = customerService.getAll();
        List<Book> books = bookService.getAll();

        Customer customer = customers.get(0);

        Book book1 = books.get(0);
        Book book2 = books.get(1);
        Book book3 = books.get(2);

        BigDecimal bill = BigDecimal.ZERO;
        try {
            bill = rentalService.rent(customer, book1, book2, book3);
        } catch (BookUnavailableException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Bill: " + bill.toPlainString());

        rentalService.getAll().forEach(System.out::println);

    }

    private void returnBooks(){
        List<RentTransaction> transactions = rentalService.getAll();
        transactions.forEach(System.out::println);

        RentTransaction transaction = transactions.get(0);

        try {
            boolean succeed = rentalService.returnBooks(transaction);
            System.out.println("Succeed returning all book: " + succeed);
        } catch (RentOutdatedException e) {
            System.out.println(e.getMessage());
        }
    }


    public static void main(String[] args) {

        ApplicationContext xmlContext = new ClassPathXmlApplicationContext("config.xml");
        ApplicationContext annotationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);

        Main main = new Main();

        // You can choose the ApplicationContext (xml based or annotation based)
        main.initService(annotationContext);

        // uncomment to run the services
        main.createCustomers();
//        main.createBooks();
//        main.rent();
//        main.returnBooks();

    }

}
