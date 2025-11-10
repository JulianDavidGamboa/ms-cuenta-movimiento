package com.mybank.mscuentamovimiento.application.service;

import com.mybank.mscuentamovimiento.application.dto.ClientDto;
import com.mybank.mscuentamovimiento.domain.model.Account;
import com.mybank.mscuentamovimiento.domain.port.out.AccountRepositoryPort;
import com.mybank.mscuentamovimiento.domain.usecase.AccountUseCase;
import com.mybank.mscuentamovimiento.infrastrcuture.feign.ClientFeignClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements AccountUseCase {

    private final AccountRepositoryPort repositoryPort;
    private final ClientFeignClient clientFeignClient;

    public AccountService(AccountRepositoryPort repositoryPort, ClientFeignClient clientFeignClient) {
        this.repositoryPort = repositoryPort;
        this.clientFeignClient = clientFeignClient;
    }

    @Override
    @CircuitBreaker(name = "clientValidation")
    public Account create(Account account) {
        ClientDto clientDto = clientFeignClient.getClient(account.getClientId());
        if (clientDto == null) {
            throw new RuntimeException("Client not found");
        }
        return repositoryPort.save(account);
    }

    @Override
    public Account update(Account account) {
        return repositoryPort.save(account);
    }

    @Override
    public void deleteByAccountNumber(String accountNumber) {
        repositoryPort.deleteByAccountNumber(accountNumber);
    }

    @Override
    public Optional<Account> findByAccountNumber(String accountNumber) {
        return repositoryPort.findByAccountNumber(accountNumber);
    }

    @Override
    public List<Account> findAll() {
        return repositoryPort.findAll();
    }

    @Override
    public List<Account> findByClientId(String clientId) {
        return repositoryPort.findByClientId(clientId);
    }
}
