package com.mybank.mscuentamovimiento.domain.service;

import com.mybank.mscuentamovimiento.domain.exception.AccountException;
import com.mybank.mscuentamovimiento.domain.model.Account;
import com.mybank.mscuentamovimiento.domain.model.AccountType;
import com.mybank.mscuentamovimiento.domain.repository.AccountRepository;

import java.math.BigDecimal;

public class AccountDomainService {
    private final AccountRepository accountRepository;

    public AccountDomainService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account createAccount(String accountNumber, AccountType accountType, BigDecimal initialBalance, String clientId) {
        if (accountRepository.existsByAccountNumber(accountNumber)) {
            throw new AccountException("Ya existe una cuenta con el número");
        }

        Account cuenta = new Account(accountNumber, accountType, initialBalance, clientId);
        return accountRepository.save(cuenta);
    }

    public void validateActiveAccount(Account account) {
        if (!account.isActive()) {
            throw new AccountException("La cuenta no está activa");
        }
    }
}
