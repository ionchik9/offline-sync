create table sessions
(
    "sessionId"  uuid      default uuid_generate_v4()         not null
        constraint "PK_ba57f8421edf5e5c4e99b833811"
            primary key,
    token        varchar                                      not null,
    platform     varchar   default 'none'::character varying  not null,
    "expiredAt"  timestamp                                    not null,
    "createdAt"  timestamp default now()                      not null,
    "updatedAt"  timestamp default now()                      not null,
    "userId"     uuid
        constraint "FK_57de40bc620f456c7311aa3a1e6"
            references users,
    "appVersion" varchar   default '0.0.0'::character varying not null,
    "deviceId"   varchar   default 'none'::character varying  not null,
    device       varchar   default 'none'::character varying  not null,
    locale       varchar   default 'no'::character varying    not null,
    active       boolean   default true                       not null,
    "apiVersion" varchar   default 'v0'::character varying    not null
);


create index "IDX_e9f62f5dcb8a54b84234c9e7a0"
    on sessions (token);

create table stripe_accounts
(
    id                    uuid      default uuid_generate_v4() not null
        constraint "PK_e2022498b81a00e4c9e5b00f418"
            primary key,
    "userId"              uuid                                 not null,
    "accountId"           varchar                              not null,
    active                boolean   default false,
    "onBoardingCompleted" boolean   default false,
    "paymentsEnabled"     boolean   default false,
    "createdAt"           timestamp default now()              not null,
    "updatedAt"           timestamp default now()              not null
);

create table subscriptions
(
    id                uuid      default uuid_generate_v4() not null
        constraint "PK_a87248d73155605cf782be9ee5e"
            primary key,
    "purchaseId"      uuid                                 not null,
    "purchaseTokenId" text,
    "externalUserId"  varchar                              not null,
    "userId"          varchar                              not null,
    environment       varchar                              not null,
    platform          varchar                              not null,
    active            boolean                              not null,
    "expiredAt"       timestamp,
    "productId"       varchar                              not null,
    revenue           double precision                     not null,
    status            varchar                              not null,
    reason            varchar                              not null,
    "updatedAt"       timestamp default now()              not null,
    "createdAt"       timestamp default now()              not null,
    type              varchar   default 'inapp'::character varying
);



