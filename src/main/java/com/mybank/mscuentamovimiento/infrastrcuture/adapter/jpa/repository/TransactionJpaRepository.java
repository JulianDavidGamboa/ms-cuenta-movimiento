package com.mybank.mscuentamovimiento.infrastrcuture.adapter.jpa.repository;

import com.mybank.mscuentamovimiento.infrastrcuture.adapter.jpa.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface TransactionJpaRepository extends JpaRepository<TransactionEntity, String> {
    @Query("SELECT t FROM TransactionEntity t WHERE t.accountNumber = ?1 AND t.date BETWEEN ?2 AND ?3")
    List<TransactionEntity> findByAccountNumberAndDateBetween(String accountNumber, Date start, Date end);
}
