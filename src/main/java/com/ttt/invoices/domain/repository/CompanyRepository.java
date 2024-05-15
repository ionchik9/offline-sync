package com.ttt.invoices.domain.repository;


import com.ttt.invoices.domain.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CompanyRepository extends JpaRepository<Company, UUID>, AbstractAccountingEntityRepository<Company> {


}
