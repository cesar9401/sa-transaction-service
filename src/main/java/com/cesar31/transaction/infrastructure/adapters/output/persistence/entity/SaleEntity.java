package com.cesar31.transaction.infrastructure.adapters.output.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "sa_sale")
@NoArgsConstructor
public class SaleEntity {

    @Id
    @Column(name = "sale_id")
    private UUID saleId;

    @Column(name = "organization_id")
    private UUID organizationId;

    @Column(name = "client_id")
    private UUID clientId;

    @Column(name = "total_transaction_sum")
    private BigDecimal totalTransactionSum;

    @Column(name = "total_discount_sum")
    private BigDecimal totalDiscountSum;

    @Column(name = "net_total_for_transactions")
    private BigDecimal netTotalForTransactions;

    @Column(name = "net_total_paid")
    private BigDecimal netTotalPaid;

    @Column(name = "entry_date")
    private LocalDateTime entryDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cat_sale_status")
    private CategoryEntity catSaleStatus;

    public SaleEntity(BigDecimal netTotalForTransactions, BigDecimal netTotalPaid) {
        this.netTotalForTransactions = netTotalForTransactions;
        this.netTotalPaid = netTotalPaid;
    }
}
