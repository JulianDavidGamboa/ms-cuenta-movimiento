package com.mybank.mscuentamovimiento.domain.usecase;

import com.mybank.mscuentamovimiento.domain.model.Account;

import java.util.List;
import java.util.Optional;

public interface AccountUseCase {
    Account create(Account account);
    Account update(Account account);
    void deleteByAccountNumber(String accountNumber);
    Optional<Account> findByAccountNumber(String accountNumber);
    List<Account> findAll();
    List<Account> findByClientId(String clientId);
}
