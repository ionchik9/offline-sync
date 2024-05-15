create table delete_accounts
(
    id          uuid      default uuid_generate_v4() not null
        constraint "PK_8d0487965bc1837d58fec4d6a27"
            primary key,
    "userId"    uuid
        constraint "FK_8f10deBc2427d384aca7a5d464f"
            references users
            on delete cascade,
    "deletedAt" timestamp default now()              not null,
    "createdAt" timestamp default now()              not null
);