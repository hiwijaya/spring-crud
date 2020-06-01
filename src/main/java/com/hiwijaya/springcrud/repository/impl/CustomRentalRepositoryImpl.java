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
// repositoryImplementationPostfix = "Impl" is default
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


        // TODO: find a way to batch/bulk inserts with Spring Data JPA

        transaction.getDetails().forEach(detail -> entityManager.persist(detail) );

    }

    private void updateBookStatusAsRented(List<RentTransactionDetail> details){

        // TODO: find a way to batch/bulk updates with Spring Data JPA

        final String UPDATE_QUERY = "UPDATE Book SET rented = true WHERE id = :id";
        details.forEach(detail -> {
            entityManager.createQuery(UPDATE_QUERY)
                    .setParameter("id", detail.getBook().getId()).executeUpdate();
        });

    }

}
