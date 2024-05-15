package com.ttt.invoices.service;


import com.ttt.invoices.domain.dto.sync.SyncResponseDTO;
import com.ttt.invoices.domain.model.BaseEntity;
import com.ttt.invoices.domain.projection.BasicSyncProjection;
import com.ttt.invoices.domain.repository.AbstractAccountingEntityRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * Interface for synchronizing offline operations between client's devices and the Backend.
 *
 * @param <T> Entity Type to be synchronized.
 */
public interface SyncService<T extends BaseEntity> {

    /**
     * Returns the repository associated with the Entity    .
     */
    AbstractAccountingEntityRepository<T> getRepository();


    /**
     * Saves a list of updated entities to the repository, as an Upsert operation.
     *
     * @param updated            List of updated entities.
     * @param accountingEntityId ID of the accounting entity to associate with the entities being saved.
     */
    @Transactional
    default void saveEntities(List<T> updated, long accountingEntityId) {
        if (updated.isEmpty()) {
            return;
        }

        Map<UUID, T> updatedMap = updated.stream()
                .collect(Collectors.toMap(BaseEntity::getId, Function.identity()));

        List<BasicSyncProjection> foundEntities = getRepository().findByIdIn(updatedMap.keySet());

        foundEntities.forEach(existingEntity -> {
            T updatedEntity = updatedMap.get(existingEntity.getId());
            if (updatedEntity != null && updatedEntity.getUpdatedAt().isBefore(existingEntity.getUpdatedAt())) {
                updatedMap.remove(existingEntity.getId());
            }
        });

        var listToPersist = updatedMap.values();

        listToPersist.forEach(entity -> entity.setAccountingEntityId(accountingEntityId));
        getRepository().saveAll(listToPersist);
    }


    /**
     * Retrieves entities associated with a specified accounting entity ID that have been updated since a given timestamp.
     *
     * @param accEntityId ID of the accounting entity.
     * @param lastUpdated Timestamp indicating the last time synchronization was performed.
     * @param batchSize   Maximum number of entities to retrieve in a single batch.
     * @return A SyncResponseDTO containing the updated entities.
     */
    @Transactional(readOnly = true)
    default SyncResponseDTO<T> getEntities(long accEntityId, Instant lastUpdated, int batchSize) {
        Pageable pageable = PageRequest.of(0, batchSize);

        List<T> dataSlice = getRepository()
                .findByAccountingEntityIdAndUpdatedAtGreaterThanEqualOrderByUpdatedAtAsc(accEntityId, lastUpdated, pageable);

        Instant lastItemUpdateTime = !dataSlice.isEmpty() ? dataSlice.get(dataSlice.size() - 1).getUpdatedAt() : Instant.now();

        return new SyncResponseDTO<>(lastItemUpdateTime.toEpochMilli(), dataSlice);
    }
}
