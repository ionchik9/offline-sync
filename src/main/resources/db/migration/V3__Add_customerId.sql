ALTER TABLE users
    ADD COLUMN "customerId" BIGINT NULL;

CREATE UNIQUE INDEX users_customer_id_unique_idx ON users ("customerId");