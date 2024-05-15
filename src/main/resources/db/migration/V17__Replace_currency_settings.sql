alter table users
    drop column currency;

alter table users
    add currency jsonb;