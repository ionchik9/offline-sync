package com.ttt.invoices.data;


import com.ttt.invoices.domain.model.Client;
import com.ttt.invoices.domain.model.Company;
import com.ttt.invoices.domain.model.Item;
import com.ttt.invoices.domain.repository.ClientRepository;
import com.ttt.invoices.domain.repository.CompanyRepository;
import com.ttt.invoices.domain.repository.ItemRepository;
import org.instancio.Instancio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.instancio.Select.field;

@Service
@Transactional
public class TestDataManager {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ClientRepository clientRepository;


    @Transactional
    public Company saveRandomActiveCompany(){
        return companyRepository.save(Instancio
                .of(Company.class)
                .set(field(Company::isArchived), false)
                .create());
    }

    public Item saveRandomItem(){
        return itemRepository.save(Instancio
                .of(Item.class)
                .set(field(Item::isArchived), false)
                .create());
    }

    public Client saveRandomActiveClient(){
        return clientRepository.save(Instancio
                .of(Client.class)
                .set(field(Client::isArchived), false)
                .create());
    }


}
