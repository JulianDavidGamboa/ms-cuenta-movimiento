package com.mybank.mscuentamovimiento.domain.model;

public class ClientProjection {
    private String clientId;
    private String name;

    public ClientProjection() {}

    public ClientProjection(String clientId, String name) {
        this.clientId = clientId;
        this.name = name;
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
