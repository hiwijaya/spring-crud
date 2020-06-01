package com.hiwijaya.springcrud.repository;

import com.hiwijaya.springcrud.entity.RentTransaction;
import com.hiwijaya.springcrud.util.RentStatus;

/**
 * @author Happy Indra Wijaya
 */
public interface CustomRentalRepository {

    RentTransaction rent(RentTransaction transaction);

    void updateStatus(Long transactionId, RentStatus status);

}
