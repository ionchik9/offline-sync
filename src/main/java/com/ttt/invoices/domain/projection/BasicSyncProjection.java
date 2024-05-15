package com.ttt.invoices.domain.projection;

import java.time.Instant;
import java.util.UUID;

public interface BasicSyncProjection {
    UUID getId();

    Instant getUpdatedAt();
}
