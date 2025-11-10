package com.mybank.mscuentamovimiento.infrastrcuture.adapter.jpa.repository;

import com.mybank.mscuentamovimiento.infrastrcuture.adapter.jpa.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountJpaRepository extends JpaRepository<AccountEntity, String> {
    @Query("SELECT a FROM AccountEntity a WHERE a.clientId = ?1")
    List<AccountEntity> findByClientId(String clientId);
}
