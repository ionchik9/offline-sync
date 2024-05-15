package com.ttt.invoices.service.sync;

import com.ttt.invoices.domain.dto.sync.SyncResponseDTO;
import com.ttt.invoices.domain.model.BaseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Map;

/**
 * Context class that handles synchronization strategies for various entity types.
 * This class provides methods to save and retrieve entities using the appropriate synchronization
 * strategy based on the entity type.
 */
@Service
@RequiredArgsConstructor
public class SyncStrategyContext {
    /**
     * Map that holds synchronization strategies keyed by the entity class type.
     */
    private final Map<Class<?>, SyncStrategy<?>> syncStrategiesMap;

    /**
     * Saves a list of updated entities using the appropriate synchronization strategy.
     *
     * @param clazz the class type of the entities.
     * @param updated a list of updated entities to be saved.
     * @param accountingEntityId the ID of the accounting entity associated with the entities.
     * @param <T> the type of entities extending BaseEntity.
     */
    @SuppressWarnings({"unchecked"})
    public <T extends BaseEntity>  void saveEntities(Class<T> clazz, List<T> updated, long accountingEntityId){
        var strategy = (SyncStrategy<T>) syncStrategiesMap.get(clazz);
        strategy.saveEntities(updated, accountingEntityId);
    }


    /**
     * Retrieves a batch of updated entities using the appropriate synchronization strategy.
     *
     * @param clazz the class type of the entities.
     * @param accountingEntityId the ID of the accounting entity associated with the entities.
     * @param lastUpdated the timestamp indicating the last time synchronization was performed.
     * @param batchSize the maximum number of entities to retrieve in a single batch.
     * @param <T> the type of entities extending BaseEntity.
     * @return a SyncResponseDTO containing the updated entities.
     */
    @SuppressWarnings("unchecked")
    public <T extends BaseEntity>  SyncResponseDTO<T> getEntities(Class<T> clazz, long accountingEntityId, Instant lastUpdated, int batchSize){
        return (SyncResponseDTO<T>) syncStrategiesMap.get(clazz).getEntities(accountingEntityId, lastUpdated, batchSize);
    }

}
