package com.cesar31.transaction.application.ports.output;

import com.cesar31.transaction.application.dto.DishDto;
import com.cesar31.transaction.application.dto.UpdateDishStockReqDto;

import java.util.List;
import java.util.UUID;

public interface DishOutputPort {

    List<DishDto> checkStock(String dishIds);

    List<UUID> updDishStock(UpdateDishStockReqDto reqDto);
}
