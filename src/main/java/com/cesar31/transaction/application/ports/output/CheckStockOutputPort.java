package com.cesar31.transaction.application.ports.output;

import com.cesar31.transaction.application.dto.DishDto;

import java.util.List;

public interface CheckStockOutputPort {

    List<DishDto> checkStock(String dishIds);
}
