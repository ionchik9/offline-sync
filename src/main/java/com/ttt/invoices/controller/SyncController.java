package com.ttt.invoices.controller;


import com.ttt.invoices.domain.dto.sync.SyncRequestDTO;
import com.ttt.invoices.domain.dto.sync.SyncResponseDTO;
import com.ttt.invoices.domain.model.Client;
import com.ttt.invoices.domain.model.Company;
import com.ttt.invoices.domain.model.Item;
import com.ttt.invoices.domain.model.PaymentMethod;
import com.ttt.invoices.service.sync.client.ClientsSyncService;
import com.ttt.invoices.service.sync.company.CompaniesSyncService;
import com.ttt.invoices.service.sync.item.ItemsSyncService;
import com.ttt.invoices.service.sync.paymentmethod.PaymentMethodsSyncService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@Slf4j
@RestController
@RequestMapping("v4/sync/{accountingEntityId}")
@RequiredArgsConstructor
public class SyncController {
    private final ClientsSyncService clientsSyncService;
    private final CompaniesSyncService companiesSyncService;
    private final PaymentMethodsSyncService paymentMethodsSyncService;
    private final ItemsSyncService itemsSyncService;

    @PostMapping("/items")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void saveItems(@PathVariable long accountingEntityId, @RequestBody @Validated SyncRequestDTO<Item> dto) {
        log.debug("Save Items: {}.", dto);
        itemsSyncService.saveEntities(dto.getEvents(), accountingEntityId);
        log.debug("Successfully saved.");
    }

    @GetMapping("/items")
    public SyncResponseDTO<Item> getItems(@RequestParam Instant lastUpdated, @RequestParam int batchSize, @PathVariable long accountingEntityId) {
        log.debug("Get Items: {} {}.", lastUpdated, batchSize);
        return itemsSyncService.getEntities(accountingEntityId, lastUpdated, batchSize);
    }

    @PostMapping("/companies")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void saveCompanies(@PathVariable long accountingEntityId, @RequestBody @Validated SyncRequestDTO<Company> dto) {
        log.debug("Save Companies: {}.", dto);
        companiesSyncService.saveEntities(dto.getEvents(), accountingEntityId);
        log.debug("Successfully saved.");
    }

    @GetMapping("/companies")
    public SyncResponseDTO<Company> getCompanies(@RequestParam Instant lastUpdated, @RequestParam int batchSize, @PathVariable long accountingEntityId) {
        log.debug("Get Companies: {}, {}, {}", accountingEntityId, lastUpdated, batchSize);
        return companiesSyncService.getEntities(accountingEntityId, lastUpdated, batchSize);
    }

    @PostMapping("/clients")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void saveClients(@PathVariable long accountingEntityId, @RequestBody SyncRequestDTO<Client> dto){
        log.debug("Save Clients: {}.", dto);
        clientsSyncService.saveEntities(dto.getEvents(), accountingEntityId);
    }

    @GetMapping("/clients")
    public SyncResponseDTO<Client> getClients(@RequestParam Instant lastUpdated, @RequestParam int batchSize, @PathVariable long accountingEntityId) {
        log.debug("Get Clients: {}, {}, {}", accountingEntityId, lastUpdated, batchSize);
        return clientsSyncService.getEntities(accountingEntityId,  lastUpdated, batchSize);
    }

    @PostMapping("/payment-options")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void savePaymentOptions(@PathVariable long accountingEntityId,
                                   @RequestBody @Validated SyncRequestDTO<PaymentMethod> dto) {
        log.debug("Save Payment Options: {}.", dto);
        paymentMethodsSyncService.saveEntities(dto.getEvents(), accountingEntityId);
        log.debug("Successfully saved.");
    }

    @GetMapping("/payment-options")
    public SyncResponseDTO<PaymentMethod> getPaymentOptionsEvents(
            @PathVariable long accountingEntityId,
            @RequestParam Instant lastUpdated,
            @RequestParam("batchSize") int batchSize) {
        log.debug("Get Payment Options: {} {}.", lastUpdated, batchSize);
        SyncResponseDTO<PaymentMethod> dto = paymentMethodsSyncService.getEntities(accountingEntityId,  lastUpdated, batchSize);
        log.debug("DTO {}.", dto);
        return dto;
    }
}
