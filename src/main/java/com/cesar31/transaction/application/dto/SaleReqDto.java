package com.cesar31.transaction.application.dto;

import com.cesar31.transaction.application.util.SelfValidating;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class SaleReqDto extends SelfValidating {

    private UUID saleId;

    private LocalDateTime entryDate;

    @NotNull
    private UUID clientId;

    @NotNull
    private List<@Valid DishOrderReqDto> transactions;

    @NotNull
    private List<@Valid PaymentReqDto> payments;

    @Getter
    @Setter
    public static class DishOrderReqDto {

        private LocalDateTime entryDate;

        @NotNull
        private UUID dishId;

        @NotNull
        @Positive
        private Integer amount;
    }

    @Getter
    @Setter
    public static class PaymentReqDto {


        private LocalDateTime entryDate;

        @NotNull
        @Positive
        @Digits(integer = 15, fraction = 2)
        private BigDecimal amount;

        @NotNull
        @Positive
        private Long catPaymentMethod;
    }
}
