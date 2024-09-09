package com.cesar31.transaction.application.util.enums;

public enum CategoryEnum {

    SALE_STATUS(520L),
        SS_COMPLETED(521L),
        SS_CANCELED(522L),
        SS_PENDING_PAYMENT(523L),
    PAYMENT_METHOD(530L),
        PM_CASH(531L),
        PM_DEBIT_OR_CREDIT_CARD(532L),
    ;

    public final Long categoryId;

    CategoryEnum(final Long categoryId) {
        this.categoryId = categoryId;
    }
}
