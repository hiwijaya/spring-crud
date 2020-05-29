package com.hiwijaya.springcrud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author Happy Indra Wijaya
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)     // default
    private Long id;

    @Column(name = "title", length = 100, nullable = false)
    private String title;

    @Column(name = "author", length = 100, nullable = false)
    private String author;
    @Column(name = "rent_price")
    private BigDecimal rentPrice = BigDecimal.ZERO;     // dealing with 'default' value of relational mapping

    @Column(name = "rented", length = 1)
    private boolean rented;     // Y/N

    @OneToMany(mappedBy = "book")
    private List<RentTransactionDetail> transactionDetails;


    public Book(Long id){
        this.id = id;
    }


    public void setRentedString(String rented){
        this.rented = rented.equals("Y");
    }

    public String isRentedString(){
        return rented ? "Y" : "N";
    }

}
