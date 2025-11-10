package com.mybank.mscuentamovimiento.domain.usecase;

import com.mybank.mscuentamovimiento.domain.exception.InsufficientFundsException;
import com.mybank.mscuentamovimiento.domain.model.Transaction;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TransactionUseCase {
    Transaction create(Transaction transaction) throws InsufficientFundsException;
    Transaction update(Transaction transaction);
    void deleteById(String id);
    Optional<Transaction> findById(String id);
    List<Transaction> findAll();
    List<Transaction> findByAccountNumberAndDateBetween(String accountNumber, Date start, Date end);
}
