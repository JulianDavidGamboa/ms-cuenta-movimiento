package com.mybank.mscuentamovimiento.domain.model;

public enum AccountType {
    AHORRO("Ahorro"),
    CORRIENTE("Corriente");

    private final String description;

    AccountType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
