ALTER TABLE payment_methods
    ADD accounting_entity_id BIGINT;


CREATE INDEX idx_payment_methods_by_acc_entity ON payment_methods (accounting_entity_id);

