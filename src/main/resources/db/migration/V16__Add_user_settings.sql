alter table users
    drop column "docColor";

alter table users
    add "currencyFormat" varchar;

alter table users
    add "taxType" varchar default 'None'::character varying;

alter table users
    add "taxIncluded" boolean default false;

alter table users
    add tax jsonb;

alter table users
    add "dateFormat" varchar;

alter table users
    add "amountFormat" jsonb;
