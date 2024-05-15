package com.ttt.invoices.controller;


import com.ttt.invoices.domain.dto.sync.SyncRequestDTO;
import com.ttt.invoices.domain.dto.sync.SyncResponseDTO;
import com.ttt.invoices.domain.model.Client;
import com.ttt.invoices.domain.model.Company;
import com.ttt.invoices.domain.model.Item;
import com.ttt.invoices.domain.model.PaymentMethod;
import com.ttt.invoices.service.sync.SyncStrategyContext;
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
    private final SyncStrategyContext syncService;

    @PostMapping("/items")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void saveItems(@PathVariable long accountingEntityId, @RequestBody SyncRequestDTO<Item> dto) {
        log.debug("Save Items: {}.", dto);
        syncService.saveEntities(Item.class, dto.getEvents(), accountingEntityId);
        log.debug("Successfully saved.");
    }

    @GetMapping("/items")
    public SyncResponseDTO<Item> getItems(@RequestParam Instant lastUpdated, @RequestParam int batchSize, @PathVariable long accountingEntityId) {
        log.debug("Get Items: {} {}.", lastUpdated, batchSize);
        return syncService.getEntities(Item.class, accountingEntityId, lastUpdated, batchSize);
    }

    @PostMapping("/companies")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void saveCompanies(@PathVariable long accountingEntityId, @RequestBody SyncRequestDTO<Company> dto) {
        log.debug("Save Companies: {}.", dto);
        syncService.saveEntities(Company.class, dto.getEvents(), accountingEntityId);
        log.debug("Successfully saved.");
    }

    @GetMapping("/companies")
    public SyncResponseDTO<Company> getCompanies(@RequestParam Instant lastUpdated, @RequestParam int batchSize, @PathVariable long accountingEntityId) {
        log.debug("Get Companies: {}, {}, {}", accountingEntityId, lastUpdated, batchSize);
        return syncService.getEntities(Company.class, accountingEntityId, lastUpdated, batchSize);
    }

    @PostMapping("/clients")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void saveClients(@PathVariable long accountingEntityId, @RequestBody SyncRequestDTO<Client> dto){
        log.debug("Save Clients: {}.", dto);
        syncService.saveEntities(Client.class, dto.getEvents(), accountingEntityId);
    }

    @GetMapping("/clients")
    public SyncResponseDTO<Client> getClients(@RequestParam Instant lastUpdated, @RequestParam int batchSize, @PathVariable long accountingEntityId) {
        log.debug("Get Clients: {}, {}, {}", accountingEntityId, lastUpdated, batchSize);
        return syncService.getEntities(Client.class, accountingEntityId,  lastUpdated, batchSize);
    }

    @PostMapping("/payment-options")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void savePaymentOptions(@PathVariable long accountingEntityId,
                                   @RequestBody SyncRequestDTO<PaymentMethod> dto) {
        log.debug("Save Payment Options: {}.", dto);
        syncService.saveEntities(PaymentMethod.class, dto.getEvents(), accountingEntityId);
        log.debug("Successfully saved.");
    }

    @GetMapping("/payment-options")
    public SyncResponseDTO<PaymentMethod> getPaymentOptionsEvents(
            @PathVariable long accountingEntityId,
            @RequestParam Instant lastUpdated,
            @RequestParam("batchSize") int batchSize) {
        log.debug("Get Payment Options: {} {}.", lastUpdated, batchSize);
        SyncResponseDTO<PaymentMethod> dto = syncService.getEntities(PaymentMethod.class, accountingEntityId,  lastUpdated, batchSize);
        log.debug("DTO {}.", dto);
        return dto;
    }
}
