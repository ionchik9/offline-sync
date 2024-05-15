package com.ttt.invoices.domain.dto.sync;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class SyncResponseDTO<T>  {
    private final long lastUpdated;
    private final List<T> events;
}
