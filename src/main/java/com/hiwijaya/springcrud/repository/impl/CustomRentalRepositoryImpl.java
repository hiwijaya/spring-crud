package com.hiwijaya.springcrud.repository.impl;

import com.hiwijaya.springcrud.entity.RentTransaction;
import com.hiwijaya.springcrud.entity.RentTransactionDetail;
import com.hiwijaya.springcrud.repository.CustomRentalRepository;
import com.hiwijaya.springcrud.util.RentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author Happy Indra Wijaya
 */
public class CustomRentalRepositoryImpl implements CustomRentalRepository {     // repositoryImplementationPostfix = "Impl" is default

    @Autowired
    private EntityManager entityManager;


    @Transactional
    @Override
    public RentTransaction rent(RentTransaction transaction) {

            saveTransaction(transaction);

            updateBookStatus(transaction.getDetails(), true);

        return transaction;
    }


    private void saveTransaction(RentTransaction transaction){

        entityManager.persist(transaction);     // alternatively merge() can insert new objects and update existing ones.

        // auto persist with CascadeType.ALL
        //transaction.getDetails().forEach(detail -> entityManager.persist(detail) );

    }

    private void updateBookStatus(List<RentTransactionDetail> details, boolean rented){

        // TODO: find a way to batch/bulk updates with Spring Data JPA

        final String UPDATE_QUERY = "UPDATE Book SET rented = :rented WHERE id = :id";
        details.forEach(detail -> {
            entityManager.createQuery(UPDATE_QUERY)
                    .setParameter("id", detail.getBook().getId())
                    .setParameter("rented", rented)
                    .executeUpdate();
        });

    }

    @Transactional
    @Override
    public void updateStatus(Long transactionId, RentStatus status){

        final String UPDATE_STATUS_QUERY = "UPDATE RentTransaction SET status = :status WHERE id = :id";

        entityManager.createQuery(UPDATE_STATUS_QUERY)
                .setParameter("id", transactionId)
                .setParameter("status", status)
                .executeUpdate();

        if(status.equals(RentStatus.RETURNED)){
            List<RentTransactionDetail> details = getTransactionDetails(transactionId);
            updateBookStatus(details, false);
        }

    }

    private List<RentTransactionDetail> getTransactionDetails(Long transactionId){

        final String FIND_DETAILS_QUERY = "SELECT d FROM RentTransactionDetail d WHERE d.rentTransaction.id = :id";

        return entityManager.createQuery(FIND_DETAILS_QUERY, RentTransactionDetail.class)
                .setParameter("id", transactionId)
                .getResultList();

    }

}
