package com.ttt.invoices.domain.repository;


import com.ttt.invoices.domain.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID>, AbstractAccountingEntityRepository<Client> {

}
