package com.ttt.invoices;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ttt.invoices.data.TestDataManager;
import com.ttt.invoices.domain.dto.sync.SyncRequestDTO;
import com.ttt.invoices.domain.model.Client;
import com.ttt.invoices.domain.model.Company;
import com.ttt.invoices.domain.model.Item;
import com.ttt.invoices.domain.model.PaymentMethod;
import com.ttt.invoices.domain.repository.CompanyRepository;
import org.instancio.Instancio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringBootTest
public class SyncControllerTest {

    private static final String API = "/v4/sync/";

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private TestDataManager dataManager;
    @Autowired
    private CompanyRepository companyRepository;


    @Test
    public void saveCompanies() throws Exception {
        var comp = Instancio.of(Company.class)
                .create();

        var syncDto = new SyncRequestDTO<>(List.of(comp));

        long accountingEntityId = 300L;
        mockMvc.perform(post(API + "{id}/companies", accountingEntityId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(syncDto)))
                .andExpect(status().isNoContent());
    }


    @Test
    public void updateCompanies() throws Exception {

        var comp = dataManager.saveRandomActiveCompany();

        var comp2 = Instancio.of(Company.class)
                .create();

        comp2.setId(comp.getId());
        comp2.setUpdatedAt(comp.getUpdatedAt().plusSeconds(3));
        comp2.setBusinessName(comp.getBusinessName() + "test");

        var syncDto = new SyncRequestDTO<>(List.of(comp2));

        long accountingEntityId = 300L;
        mockMvc.perform(post(API + "{id}/companies", accountingEntityId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(syncDto)))
                .andExpect(status().isNoContent());


        Assertions.assertEquals(companyRepository.findById(comp.getId()).orElseThrow().getBusinessName(), comp2.getBusinessName());
    }

    @Test
    public void updateCompanies_skipOld() throws Exception {

        var comp = dataManager.saveRandomActiveCompany();
        var name = comp.getBusinessName();

        var comp2 = Instancio.of(Company.class)
                .create();

        comp2.setId(comp.getId());
        comp2.setUpdatedAt(comp.getUpdatedAt().minusSeconds(2));
        comp2.setBusinessName(comp.getBusinessName() + "test");

        var syncDto = new SyncRequestDTO<>(List.of(comp2));

        long accountingEntityId = 300L;
        mockMvc.perform(post(API + "{id}/companies", accountingEntityId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(syncDto)))
                .andExpect(status().isNoContent());


        Assertions.assertEquals(companyRepository.findById(comp.getId()).orElseThrow().getBusinessName(), name);
    }





    @Test
    public void testGetCompanies() throws Exception {
        var comp = dataManager.saveRandomActiveCompany();
        var randomComp = dataManager.saveRandomActiveCompany();

        mockMvc.perform(get(API + "{id}/companies", comp.getAccountingEntityId())
                        .queryParam("batchSize", "10")
                        .queryParam("lastUpdated", "0")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.events", hasSize(1)))
                .andExpect(jsonPath("$.events[0].id", equalTo(comp.getId().toString())));
    }

    @Test
    public void testSaveItems() throws Exception {
        var item = Instancio.of(Item.class)
                .create();

        var syncDto = new SyncRequestDTO<>(List.of(item));
        long accountingEntityId = 250L;

        mockMvc.perform(post(API + "{id}/items", accountingEntityId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(syncDto)))
                .andExpect(status().isNoContent());
    }


    @Test
    public void testGetItems() throws Exception {
        var item = dataManager.saveRandomItem();
        var randomItem = dataManager.saveRandomItem();

        mockMvc.perform(get(API + "{id}/items", item.getAccountingEntityId())
                        .queryParam("batchSize", "10")
                        .queryParam("lastUpdated", "0")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.events", hasSize(1)))
                .andExpect(jsonPath("$.events[0].id", equalTo(item.getId().toString())));
    }


    @Test
    public void testSaveClients() throws Exception {
        var inst = Instancio.of(Client.class)
                .create();
        var inst2 = Instancio.of(Client.class)
                .create();
        long accountingEntityId = 200L;

        var syncDto = new SyncRequestDTO<>(List.of(inst, inst2));

        mockMvc.perform(post(API + "{id}/clients", accountingEntityId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(syncDto)))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testGetClients() throws Exception {
        var client = dataManager.saveRandomActiveClient();
        var randomClient = dataManager.saveRandomActiveClient();

        mockMvc.perform(get(API + "{id}/clients", client.getAccountingEntityId())
                        .queryParam("batchSize", "10")
                        .queryParam("lastUpdated", "0")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.events", hasSize(1)))
                .andExpect(jsonPath("$.events[0].id", equalTo(client.getId().toString())));
    }




    @Test
    public void testSaveAndGetPaymentOptions() throws Exception {
        var inst = Instancio.of(PaymentMethod.class)
                .create();
        var inst2 = Instancio.of(PaymentMethod.class)
                .create();

        var syncDto = new SyncRequestDTO<>(List.of(inst, inst2));

        long accountingEntityId = 150L;

        mockMvc.perform(post(API + "{id}/payment-options", accountingEntityId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(syncDto)))
                .andExpect(status().isNoContent());


        mockMvc.perform(get(API + "{id}/payment-options", accountingEntityId)
                        .queryParam("batchSize", "10")
                        .queryParam("lastUpdated", "0")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.events", hasSize(2)));
    }

}