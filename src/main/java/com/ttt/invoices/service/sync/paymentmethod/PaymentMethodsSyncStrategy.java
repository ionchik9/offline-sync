package com.ttt.invoices.service.sync.paymentmethod;


import com.ttt.invoices.domain.model.PaymentMethod;
import com.ttt.invoices.domain.repository.PaymentMethodRepository;
import com.ttt.invoices.service.sync.SyncStrategy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Getter
public class PaymentMethodsSyncStrategy implements SyncStrategy<PaymentMethod> {
    private final PaymentMethodRepository repository;
}
