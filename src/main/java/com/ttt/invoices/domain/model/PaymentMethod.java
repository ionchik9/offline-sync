package com.ttt.invoices.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(of = {"id"})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payment_methods")
@SuperBuilder
public class PaymentMethod extends BaseEntity {
    private String name;
    private String payload;
    private PaymentType type;
}
