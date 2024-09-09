package com.cesar31.transaction.application.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@ToString
public class DishDto {
    private UUID dishId;
    private UUID organizationId;
    private String name;
    private String description;
    private Integer stock;
    private BigDecimal price;
}
