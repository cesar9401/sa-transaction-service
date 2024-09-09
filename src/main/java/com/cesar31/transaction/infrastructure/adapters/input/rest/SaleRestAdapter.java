package com.cesar31.transaction.infrastructure.adapters.input.rest;

import com.cesar31.transaction.application.dto.SaleReqDto;
import com.cesar31.transaction.application.ports.input.SaleUseCase;
import com.cesar31.transaction.domain.Sale;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("sales")
public class SaleRestAdapter {

    private final SaleUseCase saleUseCase;

    public SaleRestAdapter(SaleUseCase saleUseCase) {
        this.saleUseCase = saleUseCase;
    }

    @PostMapping
    @Operation(description = "Create a new sale.")
    public ResponseEntity<Sale> createSale(@RequestBody SaleReqDto reqDto) throws Exception {
        var sale = saleUseCase.createSale(reqDto);
        return ResponseEntity.ok(sale);
    }
}
