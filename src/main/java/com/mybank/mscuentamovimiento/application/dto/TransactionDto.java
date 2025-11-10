package com.mybank.mscuentamovimiento.application.dto;

import lombok.Data;

@Data
public class TransactionDto {
    private String transactionId;
    private java.util.Date date;
    private String type;
    private double amount;
    private double balanceAfter;
    private String accountNumber;
}
