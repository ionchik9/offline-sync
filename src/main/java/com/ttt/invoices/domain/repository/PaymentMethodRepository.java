package com.ttt.invoices.domain.repository;

import com.ttt.invoices.domain.model.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, UUID>, AbstractAccountingEntityRepository<PaymentMethod> {
    List<PaymentMethod> findByIdIn(List<UUID> ids);

    List<PaymentMethod> findByAccountingEntityId(long accountingEntityId);

}
