package com.cesar31.transaction.application.dto;

import com.cesar31.transaction.application.util.SelfValidating;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class SaleReqDto extends SelfValidating {

    private UUID saleId;

    @NotNull
    private UUID clientId;

    @NotNull
    private List<@Valid DishOrderReqDto> transactions;

    @NotNull
    private List<@Valid PaymentReqDto> payments;

    @Getter
    @Setter
    public static class DishOrderReqDto {

        @NotNull
        private UUID dishId;

        @NotNull
        @Positive
        private UUID amount;
    }

    @Getter
    @Setter
    public static class PaymentReqDto {

        @NotNull
        @Positive
        @Digits(integer = 15, fraction = 2)
        private BigDecimal amount;

        @NotNull
        @Positive
        private Long catPaymentMethod;
    }
}
