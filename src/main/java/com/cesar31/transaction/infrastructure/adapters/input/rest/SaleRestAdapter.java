package com.cesar31.transaction.infrastructure.adapters.input.rest;

import com.cesar31.transaction.application.dto.SaleReqDto;
import com.cesar31.transaction.application.ports.input.SaleUseCase;
import com.cesar31.transaction.domain.Sale;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("sales")
public class SaleRestAdapter {

    private final SaleUseCase saleUseCase;

    public SaleRestAdapter(SaleUseCase saleUseCase) {
        this.saleUseCase = saleUseCase;
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
}
