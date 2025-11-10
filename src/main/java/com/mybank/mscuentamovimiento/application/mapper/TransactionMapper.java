package com.mybank.mscuentamovimiento.application.mapper;

import com.mybank.mscuentamovimiento.application.dto.TransactionDto;
import com.mybank.mscuentamovimiento.domain.model.Transaction;
import com.mybank.mscuentamovimiento.infrastrcuture.adapter.jpa.entity.TransactionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    Transaction toDomain(TransactionEntity entity);
    TransactionEntity toEntity(Transaction domain);
    TransactionDto toDto(Transaction domain);
    Transaction toDomain(TransactionDto dto);
}