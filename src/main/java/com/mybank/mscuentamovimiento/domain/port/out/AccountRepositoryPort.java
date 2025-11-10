package com.mybank.mscuentamovimiento.domain.port.out;

import com.mybank.mscuentamovimiento.domain.model.Account;

import java.util.List;
import java.util.Optional;

public interface AccountRepositoryPort {
    Account save(Account account);
    Optional<Account> findByAccountNumber(String accountNumber);
    List<Account> findAll();
    List<Account> findByClientId(String clientId);
    void deleteByAccountNumber(String accountNumber);
}
