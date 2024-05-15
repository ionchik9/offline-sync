alter table invoices_companies
    drop column "createdAt";

alter table invoices_companies
    drop column "updatedAt";

alter table invoices_clients
    drop column "createdAt";

alter table invoices_clients
    drop column "updatedAt";

alter table delete_accounts
    drop column "createdAt";

alter table shares
    drop column "createdAt";

alter table shares
    drop column "updatedAt";