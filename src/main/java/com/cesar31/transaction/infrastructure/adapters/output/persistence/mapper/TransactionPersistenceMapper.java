package com.cesar31.transaction.infrastructure.adapters.output.persistence.mapper;

import com.cesar31.transaction.domain.Transaction;
import com.cesar31.transaction.infrastructure.adapters.output.persistence.entity.TransactionEntity;
import org.mapstruct.Mapping;

public interface TransactionPersistenceMapper {

    @Mapping(target = "transactionId", source = "transactionId")
    @Mapping(target = "saleId", source = "saleId")
    @Mapping(target = "quantity", source = "quantity")
    @Mapping(target = "unitPrice", source = "unitPrice")
    @Mapping(target = "unitDiscount", source = "unitDiscount")
    @Mapping(target = "transactionTotal", source = "transactionTotal")
    @Mapping(target = "discountTotal", source = "discountTotal")
    @Mapping(target = "netTotal", source = "netTotal")
    @Mapping(target = "entryDate", source = "entryDate")
    Transaction toTransaction(TransactionEntity transactionEntity);

    @Mapping(target = "transactionId", source = "transactionId")
    @Mapping(target = "saleId", source = "saleId")
    @Mapping(target = "quantity", source = "quantity")
    @Mapping(target = "unitPrice", source = "unitPrice")
    @Mapping(target = "unitDiscount", source = "unitDiscount")
    @Mapping(target = "transactionTotal", source = "transactionTotal")
    @Mapping(target = "discountTotal", source = "discountTotal")
    @Mapping(target = "netTotal", source = "netTotal")
    @Mapping(target = "entryDate", source = "entryDate")
    TransactionEntity toTransactionEntity(Transaction transaction);
}
