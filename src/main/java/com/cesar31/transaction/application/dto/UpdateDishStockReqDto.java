package com.cesar31.transaction.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class UpdateDishStockReqDto {

    private UUID clientId;
    private List<DishOrderDto> orders;

    @Getter
    @Setter
    public static class DishOrderDto {
        private UUID dishId;
        private Integer quantity;
    }
}
