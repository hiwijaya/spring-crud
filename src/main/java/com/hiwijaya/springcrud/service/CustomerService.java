package com.hiwijaya.springcrud.service;

import com.hiwijaya.springcrud.entity.Customer;
import com.hiwijaya.springcrud.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Happy Indra Wijaya
 */
@Component
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public Customer save(Customer customer){
        return repository.save(customer);
    }

    public void delete(Customer customer){
        repository.delete(customer);
    }

    public Customer getCustomerById(Integer id){
        return repository.findById(id).orElse(null);
    }

    public List<Customer> getAll(){
        return repository.findAll();
    }


}
