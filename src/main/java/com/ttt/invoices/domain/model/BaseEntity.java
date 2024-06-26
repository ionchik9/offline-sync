package com.ttt.invoices.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.Instant;
import java.util.UUID;

@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Data
public abstract class BaseEntity {
    @Id
    private UUID id;
    private boolean archived;

    @JsonIgnore
    @Column(name = "accounting_entity_id")
    private Long accountingEntityId;


    private Instant updatedAt;

    private Instant createdAt;
}
