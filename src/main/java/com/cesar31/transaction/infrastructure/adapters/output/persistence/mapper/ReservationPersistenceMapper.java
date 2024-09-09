package com.cesar31.transaction.infrastructure.adapters.output.persistence.mapper;

import com.cesar31.transaction.domain.Reservation;
import com.cesar31.transaction.infrastructure.adapters.output.persistence.entity.ReservationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = CategoryPersistenceMapper.class)
public interface ReservationPersistenceMapper {

    @Mapping(target = "transactionId", source = "transaction.transactionId")
    @Mapping(target = "saleId", source = "transaction.saleId")
    @Mapping(target = "quantity", source = "transaction.quantity")
    @Mapping(target = "unitPrice", source = "transaction.unitPrice")
    @Mapping(target = "unitDiscount", source = "transaction.unitDiscount")
    @Mapping(target = "transactionTotal", source = "transaction.transactionTotal")
    @Mapping(target = "discountTotal", source = "transaction.discountTotal")
    @Mapping(target = "netTotal", source = "transaction.netTotal")
    @Mapping(target = "entryDate", source = "transaction.entryDate")
    @Mapping(target = "roomId", source = "roomId")
    @Mapping(target = "startDate", source = "startDate")
    @Mapping(target = "endDate", source = "endDate")
    @Mapping(target = "catReservationStatus", source = "catReservationStatus")
    Reservation toReservation(ReservationEntity reservationEntity);

    @Mapping(target = "transaction.transactionId", source = "transactionId")
    @Mapping(target = "transaction.saleId", source = "saleId")
    @Mapping(target = "transaction.quantity", source = "quantity")
    @Mapping(target = "transaction.unitPrice", source = "unitPrice")
    @Mapping(target = "transaction.unitDiscount", source = "unitDiscount")
    @Mapping(target = "transaction.transactionTotal", source = "transactionTotal")
    @Mapping(target = "transaction.discountTotal", source = "discountTotal")
    @Mapping(target = "transaction.netTotal", source = "netTotal")
    @Mapping(target = "transaction.entryDate", source = "entryDate")
    @Mapping(target = "roomId", source = "roomId")
    @Mapping(target = "startDate", source = "startDate")
    @Mapping(target = "endDate", source = "endDate")
    @Mapping(target = "catReservationStatus", source = "catReservationStatus")
    ReservationEntity toReservationEntity(Reservation reservation);
}
