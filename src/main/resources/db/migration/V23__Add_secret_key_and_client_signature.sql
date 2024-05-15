alter table shares
    add "secretKey" varchar default '';

alter table invoices
    add "clientSignature" varchar default '';

alter table estimates
    add "clientSignature" varchar default '';