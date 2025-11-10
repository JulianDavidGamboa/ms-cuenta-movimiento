package com.mybank.mscuentamovimiento.application.rest;

import com.mybank.mscuentamovimiento.application.dto.ReportDto;
import com.mybank.mscuentamovimiento.application.service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class ReportController {
    private final ReportService service;

    public ReportController(ReportService service) {
        this.service = service;
    }

    @GetMapping("/reportes")
    public ResponseEntity<List<ReportDto>> getReport(
            @RequestParam String clientId,
            @RequestParam String fechaInicio, // yyyy-MM-dd
            @RequestParam String fechaFin) {

        LocalDate start = LocalDate.parse(fechaInicio);
        LocalDate end = LocalDate.parse(fechaFin);

        return ResponseEntity.ok(service.generateReport(clientId, start, end));
    }
}
