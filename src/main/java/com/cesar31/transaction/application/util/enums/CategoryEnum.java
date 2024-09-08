package com.cesar31.transaction.application.util.enums;

public enum CategoryEnum {

    SALE_STATUS(520L),
        SS_COMPLETED(521L),
        SS_CANCELED(521L),
        SS_PENDING_PAYMENT(521L),
    ;

    public final Long categoryId;

    CategoryEnum(final Long categoryId) {
        this.categoryId = categoryId;
    }
}
