package com.hiwijaya.springcrud.repository;

import com.hiwijaya.springcrud.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Happy Indra Wijaya
 */
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
