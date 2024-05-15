package com.ttt.invoices.domain.model;

import lombok.Getter;

@Getter
public enum DueType {
    NONE,
    ON_RECEIPT,
    NEXT_DAY,
    DAYS_7,
    DAYS_30,
    CUSTOM_DATE
}
