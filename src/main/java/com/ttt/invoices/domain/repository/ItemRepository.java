package com.ttt.invoices.domain.repository;


import com.ttt.invoices.domain.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ItemRepository extends JpaRepository<Item, UUID>, AbstractAccountingEntityRepository<Item> {


}
