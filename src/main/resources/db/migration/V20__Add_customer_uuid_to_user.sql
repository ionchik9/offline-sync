ALTER TABLE users
    ADD COLUMN "customerUid" VARCHAR NULL;

CREATE UNIQUE INDEX users_customer_uuid_unique_idx ON users ("customerUid");