package com.mybank.mscuentamovimiento.domain.port.out;

import com.mybank.mscuentamovimiento.domain.model.ClientProjection;

import java.util.Optional;

public interface ClientProjectionRepositoryPort {
    ClientProjection save(ClientProjection projection);
    Optional<ClientProjection> findById(String clientId);
    void deleteById(String clientId);
}
