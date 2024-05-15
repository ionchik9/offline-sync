package com.ttt.invoices.service.sync.client;


import com.ttt.invoices.domain.model.Client;
import com.ttt.invoices.domain.repository.ClientRepository;
import com.ttt.invoices.service.sync.SyncStrategy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Getter
public class ClientsSyncStrategy implements SyncStrategy<Client> {
    private final ClientRepository repository;
}
