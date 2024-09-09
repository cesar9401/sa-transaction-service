package com.cesar31.transaction.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class Sale {

    private UUID saleId;
    private UUID organizationId;
    private UUID clientId;
    private BigDecimal totalTransactionSum;
    private BigDecimal totalDiscountSum;
    private BigDecimal netTotalForTransaction;
    private BigDecimal netTotalPaid;
    private LocalDateTime entryDate;
    private Category catSaleStatus;
}
