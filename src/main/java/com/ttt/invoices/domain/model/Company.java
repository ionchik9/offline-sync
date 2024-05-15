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
@Table(name = "companies")

@SuperBuilder
public class Company extends BaseEntity{

    private String businessName;
    private String email;
    private String logo;
    private String mobilePhone;
    private String ownerName;
    private String phone;
    private String signature;
    private String website;
    private String taxNumber;
    private String terms;
}
