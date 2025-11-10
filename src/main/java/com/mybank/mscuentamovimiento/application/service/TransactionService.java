package com.mybank.mscuentamovimiento.application.service;

import com.mybank.mscuentamovimiento.domain.exception.InsufficientFundsException;
import com.mybank.mscuentamovimiento.domain.model.Account;
import com.mybank.mscuentamovimiento.domain.model.Transaction;
import com.mybank.mscuentamovimiento.domain.port.out.AccountRepositoryPort;
import com.mybank.mscuentamovimiento.domain.port.out.TransactionRepositoryPort;
import com.mybank.mscuentamovimiento.domain.usecase.TransactionUseCase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionService implements TransactionUseCase {
    private final TransactionRepositoryPort transactionRepositoryPort;
    private final AccountRepositoryPort accountRepositoryPort;

    public TransactionService(TransactionRepositoryPort transactionRepositoryPort, AccountRepositoryPort accountRepositoryPort) {
        this.transactionRepositoryPort = transactionRepositoryPort;
        this.accountRepositoryPort = accountRepositoryPort;
    }

    @Override
    @Transactional
    public Transaction create(Transaction transaction) throws InsufficientFundsException {
        Optional<Account> optionalAccount = accountRepositoryPort.findByAccountNumber(transaction.getAccountNumber());
        if (optionalAccount.isEmpty()) {
            throw new RuntimeException("Account not found");
        }
        Account account = optionalAccount.get();

        double newBalance = account.getBalance() + transaction.getAmount();
        if (newBalance < 0) {
            throw new InsufficientFundsException("Insufficient funds");
        }

        transaction.setTransactionId(UUID.randomUUID().toString());
        transaction.setDate(new Date());
        transaction.setType(transaction.getAmount() > 0 ? "DEPOSIT" : "WITHDRAWAL");
        transaction.setBalanceAfter(newBalance);

        Transaction savedTransaction = transactionRepositoryPort.save(transaction);

        account.setBalance(newBalance);
        accountRepositoryPort.save(account);

        return savedTransaction;
    }

    @Override
    public Transaction update(Transaction transaction) {
        return transactionRepositoryPort.save(transaction);
    }

    @Override
    public void deleteById(String id) {
        transactionRepositoryPort.deleteById(id);
    }

    @Override
    public Optional<Transaction> findById(String id) {
        return transactionRepositoryPort.findById(id);
    }

    @Override
    public List<Transaction> findAll() {
        return transactionRepositoryPort.findAll();
    }

    @Override
    public List<Transaction> findByAccountNumberAndDateBetween(String accountNumber, Date start, Date end) {
        return transactionRepositoryPort.findByAccountNumberAndDateBetween(accountNumber, start, end);
    }
}
