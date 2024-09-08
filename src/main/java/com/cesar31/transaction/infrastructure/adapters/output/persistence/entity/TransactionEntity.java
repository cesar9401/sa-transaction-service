package com.cesar31.transaction.infrastructure.adapters.output.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "sa_transaction")
public class TransactionEntity {

    @Id
    @Column(name = "transaction_id")
    private UUID transactionId;

    @Column(name = "sale_id")
    private UUID saleId;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    @Column(name = "unit_discount")
    private BigDecimal unitDiscount;

    @Column(name = "transaction_total")
    private BigDecimal transactionTotal;

    @Column(name = "discount_total")
    private BigDecimal discountTotal;

    @Column(name = "net_total")
    private BigDecimal netTotal;

    @Column(name = "entry_date")
    private LocalDateTime entryDate;
}
