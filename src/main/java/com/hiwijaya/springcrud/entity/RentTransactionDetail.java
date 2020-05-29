package com.hiwijaya.springcrud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author Happy Indra Wijaya
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rent_transaction_details")
public class RentTransactionDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "transaction_id", nullable = false)
    private RentTransaction rentTransaction;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;


    public void setRentTransactionOnlyId(Integer trasactionId){
        this.rentTransaction = new RentTransaction(trasactionId);
    }

    public void setBookOnlyId(Long bookId){
        this.book = new Book(bookId);
    }

}
