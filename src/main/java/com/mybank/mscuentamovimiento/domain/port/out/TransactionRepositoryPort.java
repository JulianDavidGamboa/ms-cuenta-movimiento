package com.mybank.mscuentamovimiento.domain.port.out;

import com.mybank.mscuentamovimiento.domain.model.Transaction;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TransactionRepositoryPort {
    Transaction save(Transaction transaction);
    Optional<Transaction> findById(String id);
    List<Transaction> findAll();
    List<Transaction> findByAccountNumberAndDateBetween(String accountNumber, Date start, Date end);
    void deleteById(String id);
}
