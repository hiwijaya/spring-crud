package com.hiwijaya.springcrud.service;

import com.hiwijaya.springcrud.entity.Book;
import com.hiwijaya.springcrud.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author Happy Indra Wijaya
 */
@Service
public class BookService {

    @Autowired
    private BookRepository repository;


    public Book save(Book book){
        return repository.save(book);
    }

    public List<Book> save(Book... books){
        return repository.saveAll(Arrays.asList(books));
    }

    public void delete(Book book){
        repository.delete(book);
    }

    public Book getBookById(Integer id){
        return repository.findById(id).orElse(null);
    }

    public List<Book> getBookByAuthor(String author){
        return repository.findByAuthor(author);
    }

    public List<Book> getAll(){
        return repository.findAll();
    }

}
