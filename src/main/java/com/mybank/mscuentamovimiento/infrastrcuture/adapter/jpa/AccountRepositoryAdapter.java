package com.mybank.mscuentamovimiento.infrastrcuture.adapter.jpa;

import com.mybank.mscuentamovimiento.application.mapper.AccountMapper;
import com.mybank.mscuentamovimiento.domain.model.Account;
import com.mybank.mscuentamovimiento.domain.port.out.AccountRepositoryPort;
import com.mybank.mscuentamovimiento.infrastrcuture.adapter.jpa.repository.AccountJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class AccountRepositoryAdapter implements AccountRepositoryPort {
    private final AccountJpaRepository jpaRepository;
    private final AccountMapper mapper;

    public AccountRepositoryAdapter(AccountJpaRepository jpaRepository, AccountMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Account save(Account account) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(account)));
    }

    @Override
    public Optional<Account> findByAccountNumber(String accountNumber) {
        return jpaRepository.findById(accountNumber).map(mapper::toDomain);
    }

    @Override
    public List<Account> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Account> findByClientId(String clientId) {
        return jpaRepository.findByClientId(clientId).stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public void deleteByAccountNumber(String accountNumber) {
        jpaRepository.deleteById(accountNumber);
    }
}
