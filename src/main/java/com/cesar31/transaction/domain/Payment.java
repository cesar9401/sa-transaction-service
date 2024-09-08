package com.cesar31.transaction.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class Payment {

    private UUID paymentId;
    private UUID saleId;
    private BigDecimal netTotal;
    private LocalDateTime entryDate;
    private Category catPaymentMethod;
}
