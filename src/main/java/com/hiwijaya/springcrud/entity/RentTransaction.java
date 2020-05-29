package com.hiwijaya.springcrud.entity;

import com.hiwijaya.springcrud.util.RentStatus;
import com.hiwijaya.springcrud.util.StatusConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author Happy Indra Wijaya
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rent_transactions")
public class RentTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "rental_date")
    private Date rentalDate;

    @Column(name = "return_date")
    private Date returnDate;

    @Column(name = "total")
    private BigDecimal total = BigDecimal.ZERO;

    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    @Convert(converter = StatusConverter.class)
    private RentStatus status;      // 0, 1, 2

    @OneToMany(mappedBy = "rentTransaction")
    private List<RentTransactionDetail> details;


    public RentTransaction(Integer id){
        this.id = id;
    }


    public void setCustomerOnlyId(Integer customerId){
        this.customer = new Customer(customerId);
    }

}
