package com.mybank.mscuentamovimiento.application.dto;

import lombok.Data;

@Data
public class AccountDto {
    private String accountNumber;
    private String accountType;
    private double balance;
    private double initialBalance;
    private boolean active;
    private String clientId;
}
