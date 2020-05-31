package com.hiwijaya.springcrud.repository;

import com.hiwijaya.springcrud.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Happy Indra Wijaya
 */
public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findByAuthor(String author);

}
