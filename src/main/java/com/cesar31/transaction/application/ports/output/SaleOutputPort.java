package com.cesar31.transaction.application.ports.output;

import com.cesar31.transaction.application.dto.OrganizationIncomeDto;
import com.cesar31.transaction.application.dto.TransactionReportDto;
import com.cesar31.transaction.domain.DishOrder;
import com.cesar31.transaction.domain.Payment;
import com.cesar31.transaction.domain.Sale;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SaleOutputPort {

    List<Sale> findAllByQuery(UUID organizationId, UUID clientId, Long catSaleStatus);

    Optional<Sale> findByOrganizationIdAndSaleId(UUID organizationId, UUID saleId);

    Sale save(Sale sale, List<DishOrder> orders, List<Payment> payments);

    List<OrganizationIncomeDto> getTopOrganizationByIncome(LocalDateTime start, LocalDateTime end);

    List<TransactionReportDto> getTransactionByClient(UUID clientId, UUID organizationId, LocalDateTime start, LocalDateTime end);
}
