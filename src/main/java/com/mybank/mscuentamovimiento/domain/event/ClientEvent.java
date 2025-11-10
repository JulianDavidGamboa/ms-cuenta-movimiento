package com.mybank.mscuentamovimiento.domain.event;

import java.io.Serializable;

public class ClientEvent implements Serializable {
    private String eventId;
    private String eventType;
    private String clientId;
    private String name;

    public ClientEvent() {}

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
