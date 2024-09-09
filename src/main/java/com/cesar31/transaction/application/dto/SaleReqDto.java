package com.cesar31.transaction.application.dto;

import com.cesar31.transaction.application.util.SelfValidating;
import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
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

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
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

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
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
