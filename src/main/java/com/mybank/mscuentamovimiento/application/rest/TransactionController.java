package com.mybank.mscuentamovimiento.application.rest;

import com.mybank.mscuentamovimiento.application.dto.TransactionDto;
import com.mybank.mscuentamovimiento.application.mapper.TransactionMapper;
import com.mybank.mscuentamovimiento.application.service.TransactionService;
import com.mybank.mscuentamovimiento.domain.model.Transaction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/movimientos")
public class TransactionController {
    private final TransactionService service;
    private final TransactionMapper mapper;

    public TransactionController(TransactionService service, TransactionMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public TransactionDto create(@RequestBody TransactionDto dto) {
        return mapper.toDto(service.create(mapper.toDomain(dto)));
    }

    @PutMapping("/{id}")
    public TransactionDto update(@PathVariable String id, @RequestBody TransactionDto dto) {
        Transaction domain = mapper.toDomain(dto);
        domain.setTransactionId(id);
        return mapper.toDto(service.update(domain));
    }

    @GetMapping
    public List<TransactionDto> findAll() {
        return service.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDto> findById(@PathVariable String id) {
        return service.findById(id)
                .map(transaction -> ResponseEntity.ok(mapper.toDto(transaction)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.deleteById(id);
    }
}
