package com.cesar31.transaction.infrastructure.adapters.output.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "sa_payment")
public class PaymentEntity {

    @Id
    @Column(name = "payment_id")
    private UUID paymentId;

    @Column(name = "sale_id")
    private UUID saleId;

    @Column(name = "net_total")
    private BigDecimal netTotal;

    @Column(name = "entry_date")
    private LocalDateTime entryDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cat_payment_method")
    private CategoryEntity catPaymentMethod;
}
