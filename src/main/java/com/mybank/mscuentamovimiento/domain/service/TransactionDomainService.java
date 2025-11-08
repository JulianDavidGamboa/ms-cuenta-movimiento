package com.mybank.mscuentamovimiento.domain.service;

import com.mybank.mscuentamovimiento.domain.exception.AccountException;
import com.mybank.mscuentamovimiento.domain.exception.InsufficientFundsException;
import com.mybank.mscuentamovimiento.domain.model.Account;
import com.mybank.mscuentamovimiento.domain.model.Transaction;
import com.mybank.mscuentamovimiento.domain.model.TransactionType;
import com.mybank.mscuentamovimiento.domain.repository.AccountRepository;
import com.mybank.mscuentamovimiento.domain.repository.TransactionRepository;

import java.math.BigDecimal;

public class TransactionDomainService {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public TransactionDomainService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public Transaction createTransaction(Long accountId, TransactionType transactionType,
                                             BigDecimal value) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountException("Cuenta no encontrada"));

        if (!account.isActive()) {
            throw new com.mybank.mscuentamovimiento.domain.exception.AccountException("La cuenta no está activa");
        }

        BigDecimal valorConSigno = value.multiply(
                BigDecimal.valueOf(transactionType.getMultiplier())
        );

        if (!account.haveSufficientBalance(valorConSigno)) {
            throw new InsufficientFundsException("Saldo no disponible");
        }

        account.applyTransaction(valorConSigno);
        accountRepository.save(account);

        Transaction movimiento = new Transaction(
                transactionType,
                value,
                account.getActualBalance(),
                accountId
        );

        return transactionRepository.save(movimiento);
    }
}
