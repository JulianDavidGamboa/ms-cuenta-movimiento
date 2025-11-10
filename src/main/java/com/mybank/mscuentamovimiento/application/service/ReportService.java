package com.mybank.mscuentamovimiento.application.service;

import com.mybank.mscuentamovimiento.application.dto.ReportDto;
import com.mybank.mscuentamovimiento.domain.model.Account;
import com.mybank.mscuentamovimiento.domain.model.ClientProjection;
import com.mybank.mscuentamovimiento.domain.model.Transaction;
import com.mybank.mscuentamovimiento.domain.port.out.AccountRepositoryPort;
import com.mybank.mscuentamovimiento.domain.port.out.ClientProjectionRepositoryPort;
import com.mybank.mscuentamovimiento.domain.port.out.TransactionRepositoryPort;
import com.mybank.mscuentamovimiento.domain.usecase.ReportUseCase;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReportService implements ReportUseCase {
    private final AccountRepositoryPort accountRepositoryPort;
    private final TransactionRepositoryPort transactionRepositoryPort;
    private final ClientProjectionRepositoryPort clientProjectionRepositoryPort;

    public ReportService(AccountRepositoryPort accountRepositoryPort, TransactionRepositoryPort transactionRepositoryPort, ClientProjectionRepositoryPort clientProjectionRepositoryPort) {
        this.accountRepositoryPort = accountRepositoryPort;
        this.transactionRepositoryPort = transactionRepositoryPort;
        this.clientProjectionRepositoryPort = clientProjectionRepositoryPort;
    }

    @Override
    @Cacheable(value = "reports", key = "#clientId + #startDate + #endDate")
    public List<ReportDto> generateReport(String clientId, LocalDate startDate, LocalDate endDate) {
        ClientProjection projection = clientProjectionRepositoryPort.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client projection not found"));

        List<Account> accounts = accountRepositoryPort.findByClientId(clientId);
        List<ReportDto> reports = new ArrayList<>();

        Date start = Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date end = Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).plusDays(1).toInstant());

        for (Account account : accounts) {
            List<Transaction> transactions = transactionRepositoryPort.findByAccountNumberAndDateBetween(account.getAccountNumber(), start, end);
            for (Transaction transaction : transactions) {
                ReportDto dto = new ReportDto();
                dto.setDate(new SimpleDateFormat("dd/MM/yyyy").format(transaction.getDate()));
                dto.setClientName(projection.getName());
                dto.setAccountNumber(account.getAccountNumber());
                dto.setAccountType(account.getAccountType());
                dto.setInitialBalance(account.getInitialBalance());
                dto.setActive(account.isActive());
                dto.setTransactionAmount(transaction.getAmount());
                dto.setAvailableBalance(transaction.getBalanceAfter());
                reports.add(dto);
            }
        }
        return reports;
    }
}
