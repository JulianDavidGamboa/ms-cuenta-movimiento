package com.mybank.mscuentamovimiento.application.rest;

import com.mybank.mscuentamovimiento.application.dto.AccountDto;
import com.mybank.mscuentamovimiento.application.mapper.AccountMapper;
import com.mybank.mscuentamovimiento.application.service.AccountService;
import com.mybank.mscuentamovimiento.domain.model.Account;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService service;
    private final AccountMapper mapper;

    public AccountController(AccountService service, AccountMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public AccountDto create(@RequestBody AccountDto dto) {
        return mapper.toDto(service.create(mapper.toDomain(dto)));
    }

    @PutMapping("/{accountNumber}")
    public AccountDto update(@PathVariable String accountNumber, @RequestBody AccountDto dto) {
        Account domain = mapper.toDomain(dto);
        domain.setAccountNumber(accountNumber);
        return mapper.toDto(service.update(domain));
    }

    @GetMapping
    public List<AccountDto> findAll() {
        return service.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<AccountDto> findByAccountNumber(@PathVariable String accountNumber) {
        return service.findByAccountNumber(accountNumber)
                .map(account -> ResponseEntity.ok(mapper.toDto(account)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{accountNumber}")
    public void delete(@PathVariable String accountNumber) {
        service.deleteByAccountNumber(accountNumber);
    }
}
