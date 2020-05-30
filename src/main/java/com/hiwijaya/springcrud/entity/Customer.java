package com.hiwijaya.springcrud.entity;

import com.hiwijaya.springcrud.util.Gender;
import com.hiwijaya.springcrud.util.GenderConverter;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


/**
 * @author Happy Indra Wijaya
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "gender", length = 1, nullable = false)
    @Convert(converter = GenderConverter.class)
    private Gender gender;


    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<RentTransaction> rentals;


    public Customer(Integer id, String name, Gender gender) {
        this.id = id;
        this.name = name;
        this.gender = gender;
    }


    public Customer(Integer id){
        this.id = id;
    }

    // obey to Law of Demeter
    public String getGenderSymbol(){
        return gender.getSymbol();
    }

}
