package com.mybank.mscuentamovimiento.infrastrcuture.adapter.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class TransactionEntity {
    @Id
    private String transactionId;
    private java.util.Date date;
    private String type;
    private double amount;
    private double balanceAfter;
    private String accountNumber;
}