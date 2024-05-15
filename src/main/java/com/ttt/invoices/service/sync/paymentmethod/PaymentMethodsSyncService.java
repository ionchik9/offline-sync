package com.ttt.invoices.service.sync.paymentmethod;


import com.ttt.invoices.domain.model.PaymentMethod;
import com.ttt.invoices.domain.repository.PaymentMethodRepository;
import com.ttt.invoices.service.SyncService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Getter
public class PaymentMethodsSyncService implements SyncService<PaymentMethod> {
    private final PaymentMethodRepository repository;
}
