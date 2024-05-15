package com.ttt.invoices.service.sync.item;



import com.ttt.invoices.domain.model.Item;
import com.ttt.invoices.domain.repository.ItemRepository;
import com.ttt.invoices.service.SyncService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Getter
public class ItemsSyncService implements SyncService<Item> {
    private final ItemRepository repository;
}
