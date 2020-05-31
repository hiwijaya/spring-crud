package com.hiwijaya.springcrud.repository;

import com.hiwijaya.springcrud.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * @author Happy Indra Wijaya
 */
@Component
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
