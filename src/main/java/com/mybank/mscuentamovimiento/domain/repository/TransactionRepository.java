package com.mybank.mscuentamovimiento.domain.repository;

import com.mybank.mscuentamovimiento.domain.model.Transaction;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TransactionRepository {
    Transaction save(Transaction transaction);
    Optional<Transaction> findById(Long id);
    List<Transaction> findAll();
    List<Transaction> findByAccountId(Long idAccount);
    List<Transaction> findByAccountIdAndDateBetween(Long accountId, LocalDateTime start, LocalDateTime end);
    List<Transaction> findByClientIdAndDateBetween(String clientId, LocalDateTime start, LocalDateTime end);
}
