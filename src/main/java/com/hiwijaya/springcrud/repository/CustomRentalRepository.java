package com.hiwijaya.springcrud.repository;

import com.hiwijaya.springcrud.entity.RentTransaction;

/**
 * @author Happy Indra Wijaya
 */
public interface CustomRentalRepository {

    RentTransaction rent(RentTransaction transaction);

}
