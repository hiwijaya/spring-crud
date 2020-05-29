package com.hiwijaya.springcrud.service;

import com.hiwijaya.springcrud.entity.Customer;
import com.hiwijaya.springcrud.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Happy Indra Wijaya
 */
@Component
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public Customer simpan(Customer customer){
        return repository.save(customer);
    }

}
