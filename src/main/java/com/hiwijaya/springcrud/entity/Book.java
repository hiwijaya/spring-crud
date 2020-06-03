package com.hiwijaya.springcrud.entity;

import com.hiwijaya.springcrud.util.BooleanConverter;
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
    private Integer id;

    @Column(name = "title", length = 100, nullable = false)
    private String title;

    @Column(name = "author", length = 100, nullable = false)
    private String author;
    @Column(name = "rent_price")
    private BigDecimal rentPrice = BigDecimal.ZERO;     // dealing with 'default' value of relational mapping

    @Column(name = "rented", length = 1)
    @Convert(converter = BooleanConverter.class)
    private boolean rented;     // Y/N

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<RentTransactionDetail> transactionDetails;


    public Book(Integer id){
        this.id = id;
    }

    public Book(Integer id, String title, String author, BigDecimal rentPrice, boolean rented) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.rentPrice = rentPrice;
        this.rented = rented;
    }

    public void setRentedString(String rented){
        this.rented = rented.equals("Y");
    }

    public String isRentedString(){
        return rented ? "Y" : "N";
    }


    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", rentPrice=" + rentPrice +
                ", rented=" + rented +
                '}';
    }
}
