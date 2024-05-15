ALTER TABLE clients
    ADD accounting_entity_id BIGINT;


ALTER TABLE companies
    ADD accounting_entity_id BIGINT;


ALTER TABLE estimates
    ADD accounting_entity_id BIGINT;

ALTER TABLE invoices
    ADD accounting_entity_id BIGINT;


ALTER TABLE items
    ADD accounting_entity_id BIGINT;


CREATE INDEX idx_clients_by_acc_entity ON clients (accounting_entity_id);

CREATE INDEX idx_companies_by_acc_entity ON companies (accounting_entity_id);

CREATE INDEX idx_estimates_by_acc_entity ON estimates (accounting_entity_id);

CREATE INDEX idx_invoices_by_acc_entity ON invoices (accounting_entity_id);

CREATE INDEX idx_items_by_acc_entity ON items (accounting_entity_id);

