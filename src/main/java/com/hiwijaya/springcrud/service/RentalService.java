package com.hiwijaya.springcrud.service;

import com.hiwijaya.springcrud.entity.Book;
import com.hiwijaya.springcrud.entity.Customer;
import com.hiwijaya.springcrud.entity.RentTransaction;
import com.hiwijaya.springcrud.entity.RentTransactionDetail;
import com.hiwijaya.springcrud.repository.RentalRepository;
import com.hiwijaya.springcrud.util.BookUnavailableException;
import com.hiwijaya.springcrud.util.Lib;
import com.hiwijaya.springcrud.util.RentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Happy Indra Wijaya
 */
@Component
public class RentalService {

    @Autowired
    private RentalRepository repository;

    public BigDecimal rent(Customer customer, Book... books) throws BookUnavailableException {

        // check if one of the books already rented or not
        checkIfBooksAvailable(books);

        BigDecimal totalRentPrice = getTotalRentPrice(books);

        RentTransaction transaction = new RentTransaction();
        transaction.setCustomer(customer);
        transaction.setRentalDate(Lib.now());
        transaction.setReturnDate(Lib.nextWeek());
        transaction.setTotal(totalRentPrice);
        transaction.setStatus(RentStatus.RENT);

        List<RentTransactionDetail> details = new ArrayList<>();
        for(Book book : books){
            RentTransactionDetail detail = new RentTransactionDetail();
            detail.setRentTransaction(transaction);
            detail.setBook(book);
            details.add(detail);
        }
        transaction.setDetails(details);

        repository.rent(transaction);

        return totalRentPrice;

    }


    private void checkIfBooksAvailable(Book[] books) throws BookUnavailableException {
        boolean rented = Arrays.stream(books).anyMatch(book -> book.isRented());
        if(rented){
            throw new BookUnavailableException("One of the selected books is already rented.");
        }
    }

    private BigDecimal getTotalRentPrice(Book[] books){
        BigDecimal total = BigDecimal.ZERO;
        total = Arrays.stream(books).map(book -> book.getRentPrice())
                .reduce(total, BigDecimal::add);

        return total;
    }

}
