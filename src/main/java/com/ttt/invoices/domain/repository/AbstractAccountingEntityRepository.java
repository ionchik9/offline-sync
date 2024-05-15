package com.ttt.invoices.domain.repository;

import com.ttt.invoices.domain.projection.BasicSyncProjection;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public interface AbstractAccountingEntityRepository<T> {
    List<T> findByAccountingEntityIdAndUpdatedAtGreaterThanEqualOrderByUpdatedAtAsc(long accEntityId, Instant timestamp, Pageable pageable);

    List<BasicSyncProjection> findByIdIn(Iterable<UUID> ids);

    <S extends T> List<S> saveAll(Iterable<S> entities);

}
