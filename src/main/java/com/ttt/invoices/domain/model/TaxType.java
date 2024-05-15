package com.ttt.invoices.domain.model;

import lombok.Getter;

@Getter
public enum TaxType {
    NONE,
    PER_ITEM,
    ON_TOTAL
}
