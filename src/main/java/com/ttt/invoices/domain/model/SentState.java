package com.ttt.invoices.domain.model;

import lombok.Getter;

@Getter
public enum SentState {
    DRAFT,
    COMPLETED,
    PRINTED,
    SENT,
    VIEWED
}
