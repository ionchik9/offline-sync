CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

create table otp_codes
(
    id          uuid      default uuid_generate_v4() not null
        constraint "PK_9d0487965ac1837d57fec4d6a26"
            primary key,
    code        varchar                              not null,
    active      boolean   default true               not null,
    email       varchar                              not null,
    "expiredAt" timestamp                            not null,
    "createdAt" timestamp default now()              not null,
    "updatedAt" timestamp default now()              not null
);

create table users
(
    id               uuid      default uuid_generate_v4() not null
        constraint "PK_a3ffb1c0c8416b9fc6f907b7433"
            primary key,
    email            varchar                              not null,
    name             varchar,
    "createdAt"      timestamp default now()              not null,
    "updatedAt"      timestamp default now()              not null,
    "subscriptionId" uuid unique,
    source           text      default 'original'::text
);

create table payment_methods
(
    id                  uuid                    not null
        constraint "PK_34f9b8c6dfb4ac3559f7e2820d1"
            primary key,
    archived            boolean   default false not null,
    "userId"            uuid
        constraint "FK_580f1dbf7bceb9c2cde8baf7ff4"
            references users,
    "externalCreatedAt" timestamp               not null,
    "updatedAt"         timestamp default now() not null,
    "createdAt"         timestamp default now() not null,
    name                varchar,
    payload             jsonb                   not null,
    type                varchar                 not null
);

create table items
(
    id                  uuid                    not null
        constraint "PK_ba5885359424c15ca6b9e79bcf6"
            primary key,
    archived            boolean   default false not null,
    "userId"            uuid
        constraint "FK_40e681891fea5a4b3c5c2546d15"
            references users,
    "externalCreatedAt" timestamp               not null,
    "updatedAt"         timestamp default now() not null,
    "createdAt"         timestamp default now() not null,
    name                varchar                 not null,
    "isTaxable"         boolean   default true,
    price               bigint,
    tax                 jsonb,
    constraint "UQ_213736582899b3599acaade2cd1"
        unique (name, "userId")
);

create table companies
(
    id                  uuid                    not null
        constraint "PK_d4bc3e82a314fa9e29f652c2c22"
            primary key,
    archived            boolean   default false not null,
    "userId"            uuid
        constraint "FK_6d64e8c7527a9e4af83cc66cbf7"
            references users,
    "externalCreatedAt" timestamp               not null,
    "updatedAt"         timestamp default now() not null,
    "createdAt"         timestamp default now() not null,
    "businessName"      varchar                 not null,
    email               varchar,
    logo                varchar,
    "mobilePhone"       varchar,
    "ownerName"         varchar,
    phone               varchar,
    signature           varchar,
    website             varchar,
    "taxNumber"         varchar,
    terms               varchar,
    address             jsonb
);

create table clients
(
    id                  uuid                    not null
        constraint "PK_f1ab7cf3a5714dbc6bb4e1c28a4"
            primary key,
    archived            boolean   default false not null,
    "userId"            uuid
        constraint "FK_59c1e5e51addd6ebebf76230b37"
            references users,
    "externalCreatedAt" timestamp               not null,
    "updatedAt"         timestamp default now() not null,
    "createdAt"         timestamp default now() not null,
    email               varchar,
    "mobilePhone"       varchar,
    name                varchar                 not null,
    note                varchar,
    phone               varchar,
    "shippingAddress"   jsonb,
    "billingAddress"    jsonb
);

create table invoices
(
    id                         uuid                                                not null
        constraint "PK_668cef7c22a427fd822cc1be3ce"
            primary key,
    archived                   boolean          default false                      not null,
    "userId"                   uuid
        constraint "FK_fcbe490dc37a1abf68f19c5ccb9"
            references users,
    "externalCreatedAt"        timestamp                                           not null,
    "updatedAt"                timestamp        default now()                      not null,
    "createdAt"                timestamp        default now()                      not null,
    "currencyCode"             varchar,
    "currencySymbol"           varchar,
    "discountType"             varchar,
    "discountPercentage"       double precision default 0,
    "dueDate"                  timestamp,
    "dueType"                  varchar          default 'Custom_date'::character varying,
    "isPartialPaymentsAllowed" boolean                                             not null,
    "issueDate"                timestamp                                           not null,
    note                       varchar,
    number                     varchar                                             not null,
    "paymentInstructions"      varchar,
    signature                  varchar,
    terms                      varchar,
    "shippingTrackNumber"      varchar,
    "sentState"                varchar          default 'Draft'::character varying not null,
    total                      bigint,
    subtotal                   bigint,
    "discountAmount"           bigint,
    "shippingAmount"           bigint,
    "taxType"                  varchar          default 'None'::character varying,
    "taxIncluded"              boolean          default false,
    integrations               text[]           default '{}'::text[],
    photos                     text[]           default ARRAY []::text[],
    taxes                      jsonb            default '[]'::jsonb,
    "shippingAddress"          jsonb
);

create table invoices_clients
(
    id                uuid      default uuid_generate_v4() not null
        constraint "PK_eb3db9ebbafd545f0b63fa16be4"
            primary key,
    "createdAt"       timestamp default now()              not null,
    "updatedAt"       timestamp default now()              not null,
    email             varchar,
    "mobilePhone"     varchar,
    name              varchar                              not null,
    note              varchar,
    phone             varchar,
    "clientId"        uuid                                 not null,
    "invoiceId"       uuid
        constraint "REL_334e6e64df9a3f6afbeceba077"
            unique
        constraint "FK_334e6e64df9a3f6afbeceba0779"
            references invoices
            on delete cascade,
    "shippingAddress" jsonb,
    "billingAddress"  jsonb
);

create table invoices_companies
(
    id             uuid      default uuid_generate_v4() not null
        constraint "PK_9d628aa2b5189a6f1b6fe6a6ea6"
            primary key,
    "createdAt"    timestamp default now()              not null,
    "updatedAt"    timestamp default now()              not null,
    "businessName" varchar                              not null,
    email          varchar,
    logo           varchar,
    "mobilePhone"  varchar,
    "ownerName"    varchar,
    phone          varchar,
    signature      varchar,
    website        varchar,
    "taxNumber"    varchar,
    terms          varchar,
    "companyId"    uuid                                 not null,
    "invoiceId"    uuid
        constraint "REL_9f10debc2427d374aca9a5d441"
            unique
        constraint "FK_9f10debc2427d374aca9a5d441f"
            references invoices
            on delete cascade,
    address        jsonb
);

create table invoices_items
(
    id                   uuid             default uuid_generate_v4() not null
        constraint "PK_640e79fe86efc7c5f4916f3166e"
            primary key,
    "createdAt"          timestamp        default now()              not null,
    "updatedAt"          timestamp        default now()              not null,
    qty                  double precision                            not null,
    "itemName"           varchar                                     not null,
    "invoiceId"          uuid                                        not null
        constraint "FK_12f3b2dabe93f1bc120cabbf6e6"
            references invoices
            on delete cascade,
    "discountType"       varchar,
    "discountPercentage" double precision default 0,
    "order"              integer          default 0,
    total                bigint,
    subtotal             bigint,
    price                bigint,
    "discountAmount"     bigint,
    "isTaxable"          boolean          default true,
    "taxAmount"          bigint           default 0,
    tax                  jsonb
);

create table invoices_payments
(
    id          uuid      default uuid_generate_v4()         not null
        constraint "PK_6f63a54a8d94206cfd7e7976e34"
            primary key,
    "createdAt" timestamp default now()                      not null,
    "updatedAt" timestamp default now()                      not null,
    "invoiceId" uuid                                         not null
        constraint "FK_9596eed0f2ff90988c03f77cb98"
            references invoices
            on delete cascade,
    type        varchar   default 'other'::character varying not null,
    amount      bigint
);

create table estimates
(
    id                    uuid                           not null
        constraint "PK_447af75b2f6025adf7f80703810"
            primary key,
    archived              boolean          default false not null,
    "userId"              uuid
        constraint "FK_97db78591ba0170ad2fb30dfbeb"
            references users,
    "externalCreatedAt"   timestamp                      not null,
    "updatedAt"           timestamp        default now() not null,
    "createdAt"           timestamp        default now() not null,
    "currencyCode"        varchar,
    "currencySymbol"      varchar,
    "discountType"        varchar,
    "discountPercentage"  double precision default 0,
    "issueDate"           timestamp                      not null,
    note                  varchar,
    number                varchar                        not null,
    "paymentInstructions" varchar,
    signature             varchar,
    terms                 varchar,
    total                 bigint,
    subtotal              bigint,
    "discountAmount"      bigint,
    "shippingAmount"      bigint,
    "taxType"             varchar          default 'None'::character varying,
    "taxIncluded"         boolean          default false,
    photos                text[]           default ARRAY []::text[],
    taxes                 jsonb            default '[]'::jsonb,
    "shippingAddress"     jsonb
);

create table estimates_clients
(
    id                uuid      default uuid_generate_v4() not null
        constraint "PK_88fe380c8aafec7ab371e69638a"
            primary key,
    "createdAt"       timestamp default now()              not null,
    "updatedAt"       timestamp default now()              not null,
    email             varchar,
    "mobilePhone"     varchar,
    name              varchar                              not null,
    note              varchar,
    phone             varchar,
    "clientId"        uuid                                 not null,
    "estimateId"      uuid
        constraint "REL_a21a2e4f45f81fa55cad736c53"
            unique
        constraint "FK_a21a2e4f45f81fa55cad736c531"
            references estimates
            on delete cascade,
    "shippingAddress" jsonb,
    "billingAddress"  jsonb
);

create table estimates_companies
(
    id             uuid      default uuid_generate_v4() not null
        constraint "PK_41eda74f0bd06d6262f076c1b96"
            primary key,
    "createdAt"    timestamp default now()              not null,
    "updatedAt"    timestamp default now()              not null,
    "businessName" varchar                              not null,
    email          varchar,
    logo           varchar,
    "mobilePhone"  varchar,
    "ownerName"    varchar,
    phone          varchar,
    signature      varchar,
    website        varchar,
    "taxNumber"    varchar,
    terms          varchar,
    "companyId"    uuid                                 not null,
    "estimateId"   uuid
        constraint "REL_3a110ea63df0cae0d3651e6ee2"
            unique
        constraint "FK_3a110ea63df0cae0d3651e6ee29"
            references estimates
            on delete cascade,
    address        jsonb
);

create table estimates_items
(
    id                   uuid             default uuid_generate_v4() not null
        constraint "PK_d2f417c9053aed87e5bf1341047"
            primary key,
    "createdAt"          timestamp        default now()              not null,
    "updatedAt"          timestamp        default now()              not null,
    qty                  double precision                            not null,
    "itemName"           varchar                                     not null,
    "estimateId"         uuid                                        not null
        constraint "FK_5e49f855b1ebfd9fcb59ff78958"
            references estimates
            on delete cascade,
    "discountType"       varchar,
    "discountPercentage" double precision default 0,
    "order"              integer          default 0,
    total                bigint,
    subtotal             bigint,
    price                bigint,
    "discountAmount"     bigint,
    "isTaxable"          boolean          default true,
    "taxAmount"          bigint           default 0,
    tax                  jsonb
);

create table shares
(
    id                 uuid      default uuid_generate_v4()         not null
        constraint "PK_b88473409066c43c2ccb1894a82"
            primary key,
    "userId"           uuid
        constraint "FK_969e9a7c89cbbd57c889ba5f45d"
            references users,
    "updatedAt"        timestamp default now()                      not null,
    "createdAt"        timestamp default now()                      not null,
    "invoiceId"        varchar,
    "estimateId"       varchar,
    type               varchar   default 'link'::character varying  not null,
    payload            varchar   default 'unset'::character varying not null,
    "sendStatus"       varchar   default 'Sent'::character varying  not null,
    "additionalStatus" varchar   default 'none'::character varying  not null
);

INSERT INTO users VALUES ('e1a5e7ba-0186-46ae-a4d1-8068688d0b03', 'test@gmail.com', 'Alex', '2021-12-31 19:51:30.617711', '2021-12-31 19:51:30.617711', 'e1a5e7ba-0186-46ae-a4d1-8068688d0b04', 'original');


