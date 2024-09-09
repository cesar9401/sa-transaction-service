package com.cesar31.transaction.infrastructure.adapters.output.persistence.mapper;

import com.cesar31.transaction.domain.Sale;
import com.cesar31.transaction.infrastructure.adapters.output.persistence.entity.SaleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = CategoryPersistenceMapper.class)
public interface SalePersistenceMapper {

    @Mapping(target = "saleId", source = "saleId")
    @Mapping(target = "organizationId", source = "organizationId")
    @Mapping(target = "clientId", source = "clientId")
    @Mapping(target = "totalTransactionSum", source = "totalTransactionSum")
    @Mapping(target = "totalDiscountSum", source = "totalDiscountSum")
    @Mapping(target = "netTotalForTransaction", source = "netTotalForTransactions")
    @Mapping(target = "netTotalPaid", source = "netTotalPaid")
    @Mapping(target = "entryDate", source = "entryDate")
    @Mapping(target = "catSaleStatus", source = "catSaleStatus")
    Sale toSale(SaleEntity saleEntity);

    @Mapping(target = "saleId", source = "saleId")
    @Mapping(target = "organizationId", source = "organizationId")
    @Mapping(target = "clientId", source = "clientId")
    @Mapping(target = "totalTransactionSum", source = "totalTransactionSum")
    @Mapping(target = "totalDiscountSum", source = "totalDiscountSum")
    @Mapping(target = "netTotalForTransactions", source = "netTotalForTransaction")
    @Mapping(target = "netTotalPaid", source = "netTotalPaid")
    @Mapping(target = "entryDate", source = "entryDate")
    @Mapping(target = "catSaleStatus", source = "catSaleStatus")
    SaleEntity toSaleEntity(Sale sale);
}
