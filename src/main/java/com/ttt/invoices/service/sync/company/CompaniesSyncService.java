package com.ttt.invoices.service.sync.company;



import com.ttt.invoices.domain.model.Company;
import com.ttt.invoices.domain.repository.CompanyRepository;
import com.ttt.invoices.service.SyncService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Getter
public class CompaniesSyncService implements SyncService<Company> {
    private final CompanyRepository repository;
}
