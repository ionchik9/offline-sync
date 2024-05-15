package com.ttt.invoices.domain.model;

import java.time.Instant;

public class StripeAccount {
    private String id;
    private String userId;
    private String accountId;
    private boolean active;
    private boolean onBoardingCompleted;
    private boolean paymentsEnabled;
    private Instant createdAt;
    private Instant updatedAt;
}
