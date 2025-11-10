package com.mybank.mscuentamovimiento.infrastrcuture.adapter.jpa.repository;

import com.mybank.mscuentamovimiento.infrastrcuture.adapter.jpa.entity.ClientProjectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientProjectionJpaRepository extends JpaRepository<ClientProjectionEntity, String> {
}
