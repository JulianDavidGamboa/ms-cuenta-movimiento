package com.mybank.mscuentamovimiento.infrastrcuture.kafka;

public record ClientEventDto(
        String eventId,
        String eventType,
        String clientId,
        String name
) {
}
