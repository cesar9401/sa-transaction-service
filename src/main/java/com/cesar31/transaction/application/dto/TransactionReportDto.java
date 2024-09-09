package com.cesar31.transaction.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class TransactionReportDto {

    private UUID transactionId;
    private LocalDateTime entryDate;
    private BigDecimal netTotal;
    private UUID dishId;
    private UUID roomId;
    private String type;

    public TransactionReportDto(UUID transactionId, LocalDateTime entryDate, BigDecimal netTotal, UUID dishId, UUID roomId) {
        this.transactionId = transactionId;
        this.entryDate = entryDate;
        this.netTotal = netTotal;
        this.dishId = dishId;
        this.roomId = roomId;

        if (this.roomId != null) type = "Consumo en hotel";
        else type = "Consumo en restaurante";
    }
}
