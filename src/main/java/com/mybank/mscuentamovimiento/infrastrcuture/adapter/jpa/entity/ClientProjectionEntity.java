package com.mybank.mscuentamovimiento.infrastrcuture.adapter.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class ClientProjectionEntity {
    @Id
    private String clientId;
    private String name;
}
