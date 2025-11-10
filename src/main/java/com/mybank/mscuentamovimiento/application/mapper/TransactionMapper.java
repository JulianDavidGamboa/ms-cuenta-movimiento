package com.mybank.mscuentamovimiento.application.mapper;

import com.mybank.mscuentamovimiento.application.dto.TransactionDto;
import com.mybank.mscuentamovimiento.domain.model.Transaction;
import com.mybank.mscuentamovimiento.infrastrcuture.adapter.jpa.entity.TransactionEntity;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {
    public Transaction toDomain(TransactionEntity entity) {
        Transaction transaction = new Transaction();
        transaction.setTransactionId(entity.getTransactionId());
        transaction.setDate(entity.getDate());
        transaction.setType(entity.getType());
        transaction.setAmount(entity.getAmount());
        transaction.setBalanceAfter(entity.getBalanceAfter());
        transaction.setAccountNumber(entity.getAccountNumber());
        return transaction;
    }

    public TransactionEntity toEntity(Transaction domain) {
        TransactionEntity entity = new TransactionEntity();
        entity.setTransactionId(domain.getTransactionId());
        entity.setDate(domain.getDate());
        entity.setType(domain.getType());
        entity.setAmount(domain.getAmount());
        entity.setBalanceAfter(domain.getBalanceAfter());
        entity.setAccountNumber(domain.getAccountNumber());
        return entity;
    }

    public TransactionDto toDto(Transaction domain) {
        TransactionDto dto = new TransactionDto();
        dto.setTransactionId(domain.getTransactionId());
        dto.setDate(domain.getDate());
        dto.setType(domain.getType());
        dto.setAmount(domain.getAmount());
        dto.setBalanceAfter(domain.getBalanceAfter());
        dto.setAccountNumber(domain.getAccountNumber());
        return dto;
    }

    public Transaction toDomain(TransactionDto dto) {
        Transaction transaction = new Transaction();
        transaction.setTransactionId(dto.getTransactionId());
        transaction.setDate(dto.getDate());
        transaction.setType(dto.getType());
        transaction.setAmount(dto.getAmount());
        transaction.setBalanceAfter(dto.getBalanceAfter());
        transaction.setAccountNumber(dto.getAccountNumber());
        return transaction;
    }
}