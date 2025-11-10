package com.mybank.mscuentamovimiento.application.mapper;

import com.mybank.mscuentamovimiento.application.dto.AccountDto;
import com.mybank.mscuentamovimiento.domain.model.Account;
import com.mybank.mscuentamovimiento.infrastrcuture.adapter.jpa.entity.AccountEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    Account toDomain(AccountEntity entity);
    AccountEntity toEntity(Account domain);
    AccountDto toDto(Account domain);
    Account toDomain(AccountDto dto);
}
