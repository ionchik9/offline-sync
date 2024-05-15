create table doc_histories
(
    id          uuid      default uuid_generate_v4() not null
        constraint "PK_8d0487965bc1837d58fec4d6a26"
            primary key,
    "invoiceId"    uuid
        constraint "FK_8f10deBc2427d384aca8a5d474f"
            references invoices
            on delete cascade,
    type        varchar                              not null,
    "createdAt" timestamp default now()              not null
);