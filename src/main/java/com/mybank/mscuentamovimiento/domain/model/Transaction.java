package com.mybank.mscuentamovimiento.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {
    private Long id;
    private LocalDateTime dateTime;
    private TransactionType transactionType;
    private BigDecimal value;
    private BigDecimal balance;
    private Long idAccount;

    public Transaction(TransactionType transactionType, BigDecimal value, BigDecimal balance, Long idAccount) {
        this.transactionType = transactionType;
        this.value = value;
        this.balance = balance;
        this.idAccount = idAccount;
    }

    private void validateData(TransactionType transactionType, BigDecimal value,
                              BigDecimal balance, Long idAccount) {
        if (transactionType == null) {
            throw new IllegalArgumentException("El tipo de movimiento no puede ser nulo");
        }
        if (value == null || value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El valor debe ser mayor a cero");
        }
        if (balance == null) {
            throw new IllegalArgumentException("El saldo resultante no puede ser nulo");
        }
        if (idAccount == null) {
            throw new IllegalArgumentException("El cuentaId no puede ser nulo");
        }

    }

    public BigDecimal getValueWithSign() {
        return value.multiply(BigDecimal.valueOf(transactionType.getMultiplier()));
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public BigDecimal getValue() {
        return value;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public Long getIdAccount() {
        return idAccount;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
