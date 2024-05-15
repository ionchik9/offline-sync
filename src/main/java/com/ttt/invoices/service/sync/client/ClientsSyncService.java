package com.ttt.invoices.service.sync.client;


import com.ttt.invoices.domain.model.Client;
import com.ttt.invoices.domain.repository.ClientRepository;
import com.ttt.invoices.service.SyncService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Getter
public class ClientsSyncService implements SyncService<Client> {
    private final ClientRepository repository;
}
