package com.cesar31.transaction.application.ports.input;

import com.cesar31.transaction.application.dto.SaleReqDto;
import com.cesar31.transaction.domain.Sale;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SaleUseCase {

    Optional<Sale> findBySaleId(UUID saleId);

    List<Sale> findAllByQuery(UUID clientId, Long catSaleStatus);

    Sale updateSale(UUID saleId, SaleReqDto saleReqDto) throws Exception;

    Sale createSale(SaleReqDto saleReqDto) throws Exception;
}
