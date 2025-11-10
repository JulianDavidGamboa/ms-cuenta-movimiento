package com.mybank.mscuentamovimiento.application.mapper;

import com.mybank.mscuentamovimiento.application.dto.AccountDto;
import com.mybank.mscuentamovimiento.domain.model.Account;
import com.mybank.mscuentamovimiento.infrastrcuture.adapter.jpa.entity.AccountEntity;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {
    public Account toDomain(AccountEntity entity) {
        Account account = new Account();
        account.setAccountNumber(entity.getAccountNumber());
        account.setAccountType(entity.getAccountType());
        account.setInitialBalance(entity.getInitialBalance());
        account.setBalance(entity.getBalance());
        account.setActive(entity.isActive());
        account.setClientId(entity.getClientId());
        return account;
    }

    public AccountEntity toEntity(Account domain) {
        AccountEntity entity = new AccountEntity();
        entity.setAccountNumber(domain.getAccountNumber());
        entity.setAccountType(domain.getAccountType());
        entity.setInitialBalance(domain.getInitialBalance());
        entity.setBalance(domain.getBalance());
        entity.setActive(domain.isActive());
        entity.setClientId(domain.getClientId());
        return entity;
    }

    public AccountDto toDto(Account domain) {
        AccountDto dto = new AccountDto();
        dto.setAccountNumber(domain.getAccountNumber());
        dto.setAccountType(domain.getAccountType());
        dto.setInitialBalance(domain.getInitialBalance());
        dto.setBalance(domain.getBalance());
        dto.setActive(domain.isActive());
        dto.setClientId(domain.getClientId());
        return dto;
    }

    public Account toDomain(AccountDto dto) {
        Account account = new Account();
        account.setAccountNumber(dto.getAccountNumber());
        account.setAccountType(dto.getAccountType());
        account.setInitialBalance(dto.getInitialBalance());
        account.setBalance(dto.getBalance());
        account.setActive(dto.isActive());
        account.setClientId(dto.getClientId());
        return account;
    }
}
