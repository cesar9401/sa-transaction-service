package com.cesar31.transaction.infrastructure.adapters.output.persistence.mapper;

import com.cesar31.transaction.domain.Payment;
import com.cesar31.transaction.infrastructure.adapters.output.persistence.entity.PaymentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(uses = CategoryPersistenceMapper.class)
public interface PaymentPersistenceMapper {

    @Mapping(target = "paymentId", source = "paymentId")
    @Mapping(target = "saleId", source = "saleId")
    @Mapping(target = "netTotal", source = "netTotal")
    @Mapping(target = "entryDate", source = "entryDate")
    @Mapping(target = "catPaymentMethod", source = "catPaymentMethod")
    Payment toPayment(PaymentEntity paymentEntity);
    List<Payment> toPayments(List<PaymentEntity> paymentEntities);

    @Mapping(target = "paymentId", source = "paymentId")
    @Mapping(target = "saleId", source = "saleId")
    @Mapping(target = "netTotal", source = "netTotal")
    @Mapping(target = "entryDate", source = "entryDate")
    @Mapping(target = "catPaymentMethod", source = "catPaymentMethod")
    PaymentEntity toPaymentEntity(Payment payment);
    List<PaymentEntity> toPaymentEntities(List<Payment> payments);
}
