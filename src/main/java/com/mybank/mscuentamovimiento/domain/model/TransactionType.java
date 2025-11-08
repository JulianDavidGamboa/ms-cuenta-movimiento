package com.mybank.mscuentamovimiento.domain.model;

public enum TransactionType {
    DEPOSIT("Deposito", 1),
    WITHDRAWAL("Retiro", 1);

    private final String description;
    private final int multiplier;

    TransactionType(String description, int multiplier) {
        this.description = description;
        this.multiplier = multiplier;
    }

    public String getDescription() {
        return description;
    }

    public int getMultiplier() {
        return multiplier;
    }
}
