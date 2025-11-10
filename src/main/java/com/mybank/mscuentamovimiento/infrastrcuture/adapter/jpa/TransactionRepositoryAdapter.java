package com.mybank.mscuentamovimiento.infrastrcuture.adapter.jpa;

import com.mybank.mscuentamovimiento.application.mapper.TransactionMapper;
import com.mybank.mscuentamovimiento.domain.model.Transaction;
import com.mybank.mscuentamovimiento.domain.port.out.TransactionRepositoryPort;
import com.mybank.mscuentamovimiento.infrastrcuture.adapter.jpa.repository.TransactionJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class TransactionRepositoryAdapter implements TransactionRepositoryPort {
    private final TransactionJpaRepository jpaRepository;
    private final TransactionMapper mapper;

    public TransactionRepositoryAdapter(TransactionJpaRepository jpaRepository, TransactionMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Transaction save(Transaction transaction) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(transaction)));
    }

    @Override
    public Optional<Transaction> findById(String id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Transaction> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Transaction> findByAccountNumberAndDateBetween(String accountNumber, Date start, Date end) {
        return jpaRepository.findByAccountNumberAndDateBetween(accountNumber, start, end).stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public void deleteById(String id) {
        jpaRepository.deleteById(id);
    }
}
