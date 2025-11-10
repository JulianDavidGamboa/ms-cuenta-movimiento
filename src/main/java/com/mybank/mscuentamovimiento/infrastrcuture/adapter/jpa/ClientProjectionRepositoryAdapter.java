package com.mybank.mscuentamovimiento.infrastrcuture.adapter.jpa;

import com.mybank.mscuentamovimiento.domain.model.ClientProjection;
import com.mybank.mscuentamovimiento.domain.port.out.ClientProjectionRepositoryPort;
import com.mybank.mscuentamovimiento.infrastrcuture.adapter.jpa.entity.ClientProjectionEntity;
import com.mybank.mscuentamovimiento.infrastrcuture.adapter.jpa.repository.ClientProjectionJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ClientProjectionRepositoryAdapter implements ClientProjectionRepositoryPort {
    private final ClientProjectionJpaRepository jpaRepository;

    public ClientProjectionRepositoryAdapter(ClientProjectionJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public ClientProjection save(ClientProjection projection) {
        ClientProjectionEntity entity = toEntity(projection);
        ClientProjectionEntity saved = jpaRepository.save(entity);
        return toDomain(saved);
    }

    @Override
    public Optional<ClientProjection> findById(String clientId) {
        return jpaRepository.findById(clientId).map(this::toDomain);
    }

    @Override
    public void deleteById(String clientId) {
        jpaRepository.deleteById(clientId);
    }

    private ClientProjectionEntity toEntity(ClientProjection projection) {
        ClientProjectionEntity entity = new ClientProjectionEntity();
        entity.setClientId(projection.getClientId());
        entity.setName(projection.getName());
        return entity;
    }

    private ClientProjection toDomain(ClientProjectionEntity entity) {
        ClientProjection projection = new ClientProjection();
        projection.setClientId(entity.getClientId());
        projection.setName(entity.getName());
        return projection;
    }
}