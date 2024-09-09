package com.cesar31.transaction.application.ports.output;

import com.cesar31.transaction.domain.DishOrder;
import com.cesar31.transaction.domain.Payment;
import com.cesar31.transaction.domain.Sale;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SaleOutputPort {

    List<Sale> findAllByQuery(UUID organizationId, UUID clientId, Long catSaleStatus);

    Optional<Sale> findByOrganizationIdAndSaleId(UUID organizationId, UUID saleId);

    Sale save(Sale sale, List<DishOrder> orders, List<Payment> payments);
}
