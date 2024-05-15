package com.ttt.invoices.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(of = {"id"})
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "items")
@SuperBuilder
public class Item extends BaseEntity {
    private String name;
    private long price;
    private String description;
    private boolean isTaxable;
}
