package com.mybank.mscuentamovimiento.application.dto;

import lombok.Data;

@Data
public class ReportDto {
    private String date;
    private String clientName;
    private String accountNumber;
    private String accountType;
    private double initialBalance;
    private boolean active;
    private double transactionAmount;
    private double availableBalance;
}
