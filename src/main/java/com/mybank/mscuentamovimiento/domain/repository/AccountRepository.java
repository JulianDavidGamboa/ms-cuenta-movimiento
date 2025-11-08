package com.mybank.mscuentamovimiento.domain.repository;

import com.mybank.mscuentamovimiento.domain.model.Account;

import java.util.Optional;

public interface AccountRepository {
    Account save(Account account);
    Optional<Account> findById(Long id);
    Optional<Account> findByAccountNumber(String accountNumber);
    void deleteById(Long id);
    boolean existsByAccountNumber(String accountNumber);
}
