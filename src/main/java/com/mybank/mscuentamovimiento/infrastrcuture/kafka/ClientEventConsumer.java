package com.mybank.mscuentamovimiento.infrastrcuture.kafka;

import com.mybank.mscuentamovimiento.domain.model.ClientProjection;
import com.mybank.mscuentamovimiento.domain.port.out.ClientProjectionRepositoryPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ClientEventConsumer {

    private static final Logger log = LoggerFactory.getLogger(ClientEventConsumer.class);

    private final ClientProjectionRepositoryPort projectionRepositoryPort;

    public ClientEventConsumer(ClientProjectionRepositoryPort projectionRepositoryPort) {
        this.projectionRepositoryPort = projectionRepositoryPort;
    }

    @KafkaListener(topics = "client-events", groupId = "account-group")
    public void consume(ClientEventDto event) {
        log.info(">>> EVENTO KAFKA RECIBIDO: {} - Cliente: {} (ID: {}", event.eventType(), event.name(), event.clientId());
        if ("CREATED".equals(event.eventType()) || "UPDATED".equals(event.eventType())) {
            projectionRepositoryPort.save(new ClientProjection(event.clientId(), event.name()));
            log.info("Proyección guardada para cliente: {}", event.name());
        } else if ("DELETED".equals(event.eventType())) {
            projectionRepositoryPort.deleteById(event.clientId());
            log.info("Proyección eliminada para cliente ID: {}", event.clientId());
        }
    }
}