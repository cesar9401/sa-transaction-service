package com.cesar31.transaction.infrastructure.adapters.output.persistence;

import com.cesar31.transaction.application.dto.OrganizationIncomeDto;
import com.cesar31.transaction.application.ports.output.SaleOutputPort;
import com.cesar31.transaction.domain.DishOrder;
import com.cesar31.transaction.domain.Payment;
import com.cesar31.transaction.domain.Sale;
import com.cesar31.transaction.infrastructure.adapters.output.persistence.entity.SaleEntity;
import com.cesar31.transaction.infrastructure.adapters.output.persistence.mapper.DishOrderPersistenceMapper;
import com.cesar31.transaction.infrastructure.adapters.output.persistence.mapper.PaymentPersistenceMapper;
import com.cesar31.transaction.infrastructure.adapters.output.persistence.mapper.SalePersistenceMapper;
import com.cesar31.transaction.infrastructure.adapters.output.persistence.repository.DishOrderEntityRepository;
import com.cesar31.transaction.infrastructure.adapters.output.persistence.repository.PaymentEntityRepository;
import com.cesar31.transaction.infrastructure.adapters.output.persistence.repository.SaleEntityRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class SalePersistenceAdapter implements SaleOutputPort {

    private final SaleEntityRepository saleEntityRepository;
    private final SalePersistenceMapper salePersistenceMapper;
    private final DishOrderEntityRepository orderEntityRepository;
    private final PaymentEntityRepository paymentEntityRepository;
    private final DishOrderPersistenceMapper dishOrderPersistenceMapper;
    private final PaymentPersistenceMapper paymentPersistenceMapper;
    private final EntityManager em;

    public SalePersistenceAdapter(
            SaleEntityRepository saleEntityRepository,
            SalePersistenceMapper salePersistenceMapper,
            DishOrderEntityRepository orderEntityRepository,
            PaymentEntityRepository paymentEntityRepository,
            DishOrderPersistenceMapper dishOrderPersistenceMapper,
            PaymentPersistenceMapper paymentPersistenceMapper,
            EntityManager em
    ) {
        this.saleEntityRepository = saleEntityRepository;
        this.salePersistenceMapper = salePersistenceMapper;
        this.orderEntityRepository = orderEntityRepository;
        this.paymentEntityRepository = paymentEntityRepository;
        this.dishOrderPersistenceMapper = dishOrderPersistenceMapper;
        this.paymentPersistenceMapper = paymentPersistenceMapper;
        this.em = em;
    }

    @Override
    public List<Sale> findAllByQuery(UUID organizationId, UUID clientId, Long catSaleStatus) {
        var cb = em.getCriteriaBuilder();

        var cq = cb.createQuery(SaleEntity.class);
        var sale = cq.from(SaleEntity.class);

        var predicates = new ArrayList<Predicate>();
        if (organizationId != null) predicates.add(cb.equal(sale.get("organizationId"), organizationId));
        if (clientId != null) predicates.add(cb.equal(sale.get("clientId"), clientId));
        if (catSaleStatus != null) predicates.add(cb.equal(sale.get("catSaleStatus").<Long>get("categoryId"), catSaleStatus));

        cq.where(predicates.toArray(new Predicate[]{}));
        var query = em.createQuery(cq);
        var sales = query.getResultList();
        return salePersistenceMapper.toSales(sales);
    }

    @Override
    public Optional<Sale> findByOrganizationIdAndSaleId(UUID organizationId, UUID saleId) {
        return saleEntityRepository.findByOrganizationIdAndSaleId(organizationId, saleId)
                .map(salePersistenceMapper::toSale);
    }

    @Override
    @Transactional
    public Sale save(Sale sale, List<DishOrder> orders, List<Payment> payments) {
        var saleEntity = salePersistenceMapper.toSaleEntity(sale);
        var orderEntities = dishOrderPersistenceMapper.toDishOrderEntities(orders);
        var paymentEntities = paymentPersistenceMapper.toPaymentEntities(payments);

        var newSale = this.saleEntityRepository.save(saleEntity);
        this.orderEntityRepository.saveAll(orderEntities);
        this.paymentEntityRepository.saveAll(paymentEntities);
        return salePersistenceMapper.toSale(newSale);
    }

    @Override
    public List<OrganizationIncomeDto> getTopOrganizationByIncome(LocalDateTime start, LocalDateTime end) {
        var cb = em.getCriteriaBuilder();
        var query = cb.createQuery(OrganizationIncomeDto.class);

        var sale = query.from(SaleEntity.class);

        Expression<BigDecimal> totalTransactionsSum = cb.sum(sale.get("netTotalForTransactions"));
        Expression<BigDecimal> totalPaidSum = cb.sum(sale.get("netTotalPaid"));

        query.select(cb.construct(
                OrganizationIncomeDto.class,
                sale.get("organizationId"),
                totalTransactionsSum,
                totalPaidSum
        ));

        query.where(cb.between(sale.get("entryDate"), start, end));
        query.groupBy(sale.get("organizationId"));
        query.orderBy(cb.desc(cb.sum(sale.get("netTotalForTransactions"))));

        var typedQuery = em.createQuery(query);
        return typedQuery.getResultList();
    }
}
