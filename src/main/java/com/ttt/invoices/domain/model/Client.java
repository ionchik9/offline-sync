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
@Table(name = "clients")
@SuperBuilder
public class Client extends BaseEntity {
    private String email;
    private String mobilePhone;
    private String name;
    private String note;
    private String phone;
}
