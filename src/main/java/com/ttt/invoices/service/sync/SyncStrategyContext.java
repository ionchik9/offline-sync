package com.ttt.invoices.service.sync;

import com.ttt.invoices.domain.dto.sync.SyncResponseDTO;
import com.ttt.invoices.domain.model.BaseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SyncStrategyContext {
    private final Map<Class<?>, SyncStrategy<?>> syncStrategiesMap;

    public <T extends BaseEntity>  void saveEntities(Class<T> clazz, List updated, long accountingEntityId){
        syncStrategiesMap.get(clazz).saveEntities(updated, accountingEntityId);
    }

    public <T extends BaseEntity>  SyncResponseDTO<T> getEntities(Class<T> clazz, long accountingEntityId, Instant lastUpdated, int batchSize){
        return (SyncResponseDTO<T>) syncStrategiesMap.get(clazz).getEntities(accountingEntityId, lastUpdated, batchSize);
    }

}
