package com.cesar31.transaction.infrastructure.adapters.input.rest;

import com.cesar31.transaction.application.dto.OrganizationIncomeDto;
import com.cesar31.transaction.application.dto.SaleReqDto;
import com.cesar31.transaction.application.dto.TransactionReportDto;
import com.cesar31.transaction.application.ports.input.SaleUseCase;
import com.cesar31.transaction.domain.Sale;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("sales")
public class SaleRestAdapter {

    private final SaleUseCase saleUseCase;

    public SaleRestAdapter(SaleUseCase saleUseCase) {
        this.saleUseCase = saleUseCase;
    }

    @GetMapping("report1")
    @Operation(description = "Get top organizations by income")
    public ResponseEntity<List<OrganizationIncomeDto>> report1(
            @RequestParam(name = "start") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime start,
            @RequestParam(name = "end") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime end
    ) {
        var topOrgs = saleUseCase.getTopOrganizationByIncome(start, end);
        return ResponseEntity.ok(topOrgs);
    }

    @GetMapping("report2")
    @Operation(description = "Get the total consumptions by a customer in a date range")
    public ResponseEntity<List<TransactionReportDto>> report1(
            @RequestParam(name = "clientId") UUID clientId,
            @RequestParam(name = "organizationId", required = false) UUID organizationId,
            @RequestParam(name = "start") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime start,
            @RequestParam(name = "end") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime end
    ) {
        var consumptions = saleUseCase.getTransactionByClient(clientId, organizationId, start, end);
        return ResponseEntity.ok(consumptions);
    }

    @GetMapping
    @Operation(description = "Find all sales of the organization the user belongs to.")
    public ResponseEntity<List<Sale>> findAll(
            @RequestParam(required = false, name = "clientId") UUID clientId,
            @RequestParam(required = false, name = "catSaleStatus") Long catSaleStatus
    ) {
        var sales = saleUseCase.findAllByQuery(clientId, catSaleStatus);
        return ResponseEntity.ok(sales);
    }

    @GetMapping("{saleId}")
    @Operation(description = "Find any sale by its id.")
    public ResponseEntity<Sale> findById(@PathVariable("saleId") UUID saleId) {
        return saleUseCase.findBySaleId(saleId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(description = "Create a new sale.")
    public ResponseEntity<Sale> createSale(@RequestBody SaleReqDto reqDto) throws Exception {
        var sale = saleUseCase.createSale(reqDto);
        return ResponseEntity.ok(sale);
    }

    @PutMapping("{saleId}")
    @Operation(description = "Update any sale by its id.")
    public ResponseEntity<Sale> updateSale(@PathVariable("saleId") UUID saleId, @RequestBody SaleReqDto reqDto) throws Exception {
        var sale = saleUseCase.updateSale(saleId, reqDto);
        return ResponseEntity.ok(sale);
    }
}
