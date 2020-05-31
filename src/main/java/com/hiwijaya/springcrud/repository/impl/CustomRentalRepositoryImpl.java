package com.hiwijaya.springcrud.repository.impl;

import com.hiwijaya.springcrud.entity.RentTransaction;
import com.hiwijaya.springcrud.entity.RentTransactionDetail;
import com.hiwijaya.springcrud.repository.CustomRentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author Happy Indra Wijaya
 */
public class CustomRentalRepositoryImpl implements CustomRentalRepository {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    @Override
    public RentTransaction rent(RentTransaction transaction) {

            saveTransaction(transaction);

            updateBookStatusAsRented(transaction.getDetails());

        return transaction;
    }


    private void saveTransaction(RentTransaction transaction){

        entityManager.persist(transaction);     // alternatively merge() can insert new objects and update existing ones.

        //TODO: persist() doesn't perform save details.

    }

    private void updateBookStatusAsRented(List<RentTransactionDetail> details){

        final String UPDATE_QUERY = "UPDATE Book SET rented = true WHERE id = :id";

        details.forEach(d -> {
            entityManager.createQuery(UPDATE_QUERY)
                    .setParameter("id", d.getBook().getId())
                    .executeUpdate();
        });

    }

}
