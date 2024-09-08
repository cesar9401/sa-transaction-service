package com.cesar31.transaction.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class Transaction {

    private UUID transactionId;
    private UUID saleId;
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal unitDiscount;
    private BigDecimal transactionTotal;
    private BigDecimal discountTotal;
    private BigDecimal netTotal;
    private LocalDateTime entryDate;
}
