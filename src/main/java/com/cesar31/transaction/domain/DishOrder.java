package com.cesar31.transaction.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class DishOrder extends Transaction {

    private UUID dishId;
    private String foodOrderDescription;

    public DishOrder() {
        super();
    }
}
