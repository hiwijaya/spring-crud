package com.hiwijaya.springcrud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

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
    private Integer id;

    @Column(name = "title", length = 100, nullable = false)
    private String title;

    @Column(name = "author", length = 100, nullable = false)
    private String author;
    @Column(name = "rent_price")
    private BigDecimal rentPrice = BigDecimal.ZERO;     // dealing with 'default' value of relational mapping


    // TODO: use attribute converter. ref: https://thorben-janssen.com/hibernate-tips-how-to-map-a-boolean-to-y-n/
    @Column(name = "rented")
    private boolean rented;     // Y/N


    public Book(Integer id){
        this.id = id;
    }


    public void setRentedString(String rented){
        this.rented = rented.equals("Y");
    }

    public String isRentedString(){
        return rented ? "Y" : "N";
    }

}
