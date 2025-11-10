package com.mybank.mscuentamovimiento.infrastrcuture.adapter.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class AccountEntity {
    @Id
    private String accountNumber;
    private String accountType;
    private double balance;
    private double initialBalance;
    private boolean active;
    private String clientId;
}
