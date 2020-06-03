package com.hiwijaya.springcrud.repository;

import com.hiwijaya.springcrud.entity.RentTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Happy Indra Wijaya
 */
public interface RentalRepository extends JpaRepository<RentTransaction, Long>, CustomRentalRepository {

}
