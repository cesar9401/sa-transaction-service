package com.cesar31.transaction.infrastructure.adapters.input.rest;

import com.cesar31.transaction.application.ports.input.ExportUseCase;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("export")
public class ExportRestAdapter {

    private final ExportUseCase exportUseCase;

    public ExportRestAdapter(ExportUseCase exportUseCase) {
        this.exportUseCase = exportUseCase;
    }

    @GetMapping("report1")
    @Operation(description = "Get top organizations by income")
    public ResponseEntity<byte[]> report1(
            @RequestParam(name = "start") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime start,
            @RequestParam(name = "end") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime end
    ) throws Exception {
        var topOrgs = exportUseCase.getTopOrganizationByIncome(start, end);
        var headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=export.pdf");
        return new ResponseEntity<>(topOrgs, headers, HttpStatus.OK);
    }

    @GetMapping("report2")
    @Operation(description = "Get the total consumptions by a customer in a date range")
    public ResponseEntity<byte[]> report1(
            @RequestParam(name = "clientId") UUID clientId,
            @RequestParam(name = "organizationId", required = false) UUID organizationId,
            @RequestParam(name = "start") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime start,
            @RequestParam(name = "end") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime end
    ) throws Exception {
        var consumptions = exportUseCase.getTransactionByClient(clientId, organizationId, start, end);
        var headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=export.pdf");
        return new ResponseEntity<>(consumptions, headers, HttpStatus.OK);
    }
}
