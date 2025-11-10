package com.mybank.mscuentamovimiento.domain.usecase;

import com.mybank.mscuentamovimiento.application.dto.ReportDto;

import java.time.LocalDate;
import java.util.List;

public interface ReportUseCase {
    List<ReportDto> generateReport(String clientId, LocalDate startDate, LocalDate endDate);
}
