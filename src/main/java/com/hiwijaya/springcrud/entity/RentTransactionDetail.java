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
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transaction_id", nullable = false)
    private RentTransaction rentTransaction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;


    public void setRentTransactionOnlyId(Long trasactionId){
        this.rentTransaction = new RentTransaction(trasactionId);
    }

    public void setBookOnlyId(Integer bookId){
        this.book = new Book(bookId);
    }

}
