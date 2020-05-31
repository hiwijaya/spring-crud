package com.hiwijaya.springcrud.repository;

import com.hiwijaya.springcrud.entity.RentTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Happy Indra Wijaya
 */
@Repository
public interface RentalRepository extends JpaRepository<RentTransaction, Long>, CustomRentalRepository {

}
