package com.mybank.mscuentamovimiento.domain.model;

import java.math.BigDecimal;

public class Account {
    private Long id;
    private String accountNumber;
    private AccountType accountType;
    private BigDecimal initialBalance;
    private BigDecimal actualBalance;
    private Boolean status;
    private String clientId;

    public Account(Long id, String accountNumber, AccountType accountType, BigDecimal initialBalance, BigDecimal actualBalance, Boolean status, String clientId) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.initialBalance = initialBalance;
        this.actualBalance = actualBalance;
        this.status = status;
        this.clientId = clientId;
    }

    private void validateData(String accountNumber, AccountType accountType, BigDecimal initialBalance, String clientId) {
        if (accountNumber == null ||accountNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("El numero de cuenta no puede estar vacio");
        }
        if (accountType == null) {
            throw new IllegalArgumentException("El tipo de cuenta no puede ser nulo");
        }
        if (initialBalance == null || initialBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El saldo inicial no puede ser negativo");
        }
        if (clientId == null || clientId.trim().isEmpty()) {
            throw new IllegalArgumentException("El clienteId no puede estar vacío");
        }
    }

    public void applyTransaction(BigDecimal valor) {
        BigDecimal newBalance = this.actualBalance.add(valor);
        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalStateException("Saldo no disponible");
        }
        this.actualBalance = newBalance;
    }

    public void updateStatus(Boolean nuevoEstado) {
        this.status = nuevoEstado;
    }

    public boolean haveSufficientBalance(BigDecimal valor) {
        BigDecimal newBalance = this.actualBalance.add(valor);
        return newBalance.compareTo(BigDecimal.ZERO) >= 0;
    }

    public boolean isActive() {
        return this.status != null && this.status;
    }

    public Long getId() {
        return id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public BigDecimal getInitialBalance() {
        return initialBalance;
    }

    public BigDecimal getActualBalance() {
        return actualBalance;
    }

    public Boolean getStatus() {
        return status;
    }

    public String getClientId() {
        return clientId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setActualBalance(BigDecimal actualBalance) {
        this.actualBalance = actualBalance;
    }
}
