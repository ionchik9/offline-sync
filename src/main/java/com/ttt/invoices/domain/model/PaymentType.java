package com.ttt.invoices.domain.model;

import lombok.Getter;

@Getter
public enum PaymentType {
    CHECK,
    BANK_ACCOUNT,
    PAY_PAL,
    CARD,
    CASH,
    STRIPE,
    OTHER
}
