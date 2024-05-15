ALTER TABLE items
    ADD COLUMN description TEXT NULL;

ALTER TABLE users
    ADD COLUMN currency varchar(10) NULL;

ALTER TABLE users
    ADD COLUMN language varchar(50) NULL;

ALTER TABLE users
    ADD COLUMN "docColor" varchar NULL;

ALTER TABLE payment_methods
    ALTER COLUMN payload TYPE VARCHAR;
