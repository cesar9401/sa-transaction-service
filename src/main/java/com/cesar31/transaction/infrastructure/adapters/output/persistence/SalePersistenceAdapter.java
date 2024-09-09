package com.cesar31.transaction.infrastructure.adapters.output.persistence;

import com.cesar31.transaction.application.ports.output.SaleOutputPort;
import com.cesar31.transaction.domain.DishOrder;
import com.cesar31.transaction.domain.Payment;
import com.cesar31.transaction.domain.Sale;
import com.cesar31.transaction.infrastructure.adapters.output.persistence.mapper.DishOrderPersistenceMapper;
import com.cesar31.transaction.infrastructure.adapters.output.persistence.mapper.PaymentPersistenceMapper;
import com.cesar31.transaction.infrastructure.adapters.output.persistence.mapper.SalePersistenceMapper;
import com.cesar31.transaction.infrastructure.adapters.output.persistence.repository.DishOrderEntityRepository;
import com.cesar31.transaction.infrastructure.adapters.output.persistence.repository.PaymentEntityRepository;
import com.cesar31.transaction.infrastructure.adapters.output.persistence.repository.SaleEntityRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class SalePersistenceAdapter implements SaleOutputPort {

    private final SaleEntityRepository saleEntityRepository;
    private final SalePersistenceMapper salePersistenceMapper;
    private final DishOrderEntityRepository orderEntityRepository;
    private final PaymentEntityRepository paymentEntityRepository;
    private final DishOrderPersistenceMapper dishOrderPersistenceMapper;
    private final PaymentPersistenceMapper paymentPersistenceMapper;

    public SalePersistenceAdapter(SaleEntityRepository saleEntityRepository, SalePersistenceMapper salePersistenceMapper, DishOrderEntityRepository orderEntityRepository, PaymentEntityRepository paymentEntityRepository, DishOrderPersistenceMapper dishOrderPersistenceMapper, PaymentPersistenceMapper paymentPersistenceMapper) {
        this.saleEntityRepository = saleEntityRepository;
        this.salePersistenceMapper = salePersistenceMapper;
        this.orderEntityRepository = orderEntityRepository;
        this.paymentEntityRepository = paymentEntityRepository;
        this.dishOrderPersistenceMapper = dishOrderPersistenceMapper;
        this.paymentPersistenceMapper = paymentPersistenceMapper;
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
}
