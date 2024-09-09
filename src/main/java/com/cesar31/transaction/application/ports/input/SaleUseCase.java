package com.cesar31.transaction.application.ports.input;

import com.cesar31.transaction.application.dto.SaleReqDto;
import com.cesar31.transaction.domain.Sale;

public interface SaleUseCase {

    Sale createSale(SaleReqDto saleReqDto) throws Exception;
}
